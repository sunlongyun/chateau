package com.caichao.chateau.app.controller.callback;

import com.caichao.chateau.app.constants.enums.OrderStatusEnum;
import com.caichao.chateau.app.constants.enums.Validity;
import com.caichao.chateau.app.dto.OrderInfoDto;

import com.caichao.chateau.app.dto.PaymentDto;
import com.caichao.chateau.app.example.PaymentExample;
import com.caichao.chateau.app.miniProgram.enums.PayStatusEnum;
import com.caichao.chateau.app.miniProgram.request.PayOrderQuery;
import com.caichao.chateau.app.miniProgram.response.PayOrderQueryResultResponse;
import com.caichao.chateau.app.miniProgram.service.WxPayService;
import com.caichao.chateau.app.service.OrderInfoService;
import com.caichao.chateau.app.service.PaymentService;
import com.github.wxpay.sdk.WXPayUtil;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 描述:
 *
 * @AUTHOR 孙龙云
 * @date 2019-06-29 18:18
 */
@RequestMapping("callback")
@Controller
@Slf4j
public class PayCallBackController {

	@Autowired
	private WxPayService wxPayService;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private OrderInfoService orderInfoService;

	/**
	 * 支付成功回调接口
	 */
	@RequestMapping("/payNotify")
	public void payNotify() {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
			.getRequest();
		HttpServletResponse httpServletResponse = ((ServletRequestAttributes) RequestContextHolder
			.getRequestAttributes())
			.getResponse();
		httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
		httpServletResponse.setCharacterEncoding("UTF-8");

		try {
			InputStream inputStream =  request.getInputStream();
			//BufferedReader是包装设计模式，性能更搞
			BufferedReader in =  new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
			StringBuffer sb = new StringBuffer();
			//1、将微信回调信息转为字符串
			String line ;
			while ((line = in.readLine()) != null){
				sb.append(line);
			}
			in.close();
			inputStream.close();
			//2、将xml格式字符串格式转为map集合
			Map<String,String> callbackMap = WXPayUtil.xmlToMap(sb.toString());
			log.info("支付回调结果:{}",callbackMap);

			//5、判断回调信息是否成功
			if("SUCCESS".equals(callbackMap.get("result_code"))){
				//商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|* 且在同一个商户号下唯一
				String outTradeNo = callbackMap.get("out_trade_no");

				//查询支付结果
				PayOrderQuery payOrderQuery = new PayOrderQuery();
				payOrderQuery.setOutTradeNo(outTradeNo);

				PayOrderQueryResultResponse payOrderQueryResultResponse = wxPayService.queryPayOrder(payOrderQuery);
				log.info("支付结果:{}", payOrderQueryResultResponse);
				if(PayStatusEnum.SUCCESS.code().equals(payOrderQueryResultResponse.getTradeState())) {
					PaymentExample paymentExample = new PaymentExample();
					paymentExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code())
						.andPayNoEqualTo(payOrderQueryResultResponse.getOutTradeNo());

					List<PaymentDto> paymentDtoList = paymentService.getList(paymentExample);
					//更新订单状态
					if(!CollectionUtils.isEmpty(paymentDtoList)) {
						PaymentDto paymentDto = paymentDtoList.get(0);
						paymentDto.setThirdPayNo(payOrderQueryResultResponse.getTransactionId());
						paymentDto.setStatus(1);
						paymentDto.setPaySuccessTime(new Date());
						paymentService.update(paymentDto);

						String orderNo = paymentDto.getPayOrderNo();
						OrderInfoDto orderInfoDto = orderInfoService.getOrderByNo(orderNo);
						orderInfoDto.setStatus(OrderStatusEnum.PAYED.code());
						orderInfoDto.setPayNo(paymentDto.getPayNo());
						orderInfoService.update(orderInfoDto);
					}

					Map<String, String> resultMap = new HashMap<>();
					resultMap.put("return_code", "SUCCESS");
					resultMap.put("return_msg", "OK");

					String reqBody = WXPayUtil.mapToXml(resultMap);
					httpServletResponse.getWriter().write(reqBody);
				}
			}
		}catch(Exception ex){
			log.error("回调异常:", ex);
			ex.printStackTrace();
		}
	}
}
