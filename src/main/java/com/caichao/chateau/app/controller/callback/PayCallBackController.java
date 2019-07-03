package com.caichao.chateau.app.controller.callback;

import com.caichao.chateau.app.constants.enums.OrderStatusEnum;
import com.caichao.chateau.app.constants.enums.Validity;
import com.caichao.chateau.app.controller.callback.request.NotifyRequest;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	public void payNotify(NotifyRequest notifyRequest) {
		HttpServletResponse httpServletResponse = ((ServletRequestAttributes) RequestContextHolder
			.getRequestAttributes())
			.getResponse();
		log.info("支付通知结果:{}", notifyRequest);
		httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
		httpServletResponse.setCharacterEncoding("UTF-8");

		//查询支付结果
		PayOrderQuery payOrderQuery = new PayOrderQuery();
		String outTradeNo = notifyRequest.getOut_trade_no();
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
				OrderInfoDto orderInfoDto =  orderInfoService.getOrderByNo(orderNo);
				orderInfoDto.setStatus(OrderStatusEnum.PAYED.code());
				orderInfoDto.setPayNo(paymentDto.getPayNo());
				orderInfoService.update(orderInfoDto);
			}

			Map<String, String> resultMap = new HashMap<>();
			resultMap.put("return_code", "SUCCESS");
			resultMap.put("return_msg", "OK");
			try {
				String reqBody = WXPayUtil.mapToXml(resultMap);
				httpServletResponse.getWriter().write(reqBody);
			} catch(Exception ex) {
				log.error("回调失败:", ex);
			}
		}


	}
}
