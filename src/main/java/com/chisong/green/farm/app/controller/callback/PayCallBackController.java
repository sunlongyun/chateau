package com.chisong.green.farm.app.controller.callback;

import com.chisong.green.farm.app.constants.enums.OrderStatusEnum;
import com.chisong.green.farm.app.constants.enums.Validity;
import com.chisong.green.farm.app.dto.OrderInfoDto;

import com.chisong.green.farm.app.dto.PaymentDto;
import com.chisong.green.farm.app.dto.RefundOrderDto;
import com.chisong.green.farm.app.dto.RefundPaymentDto;
import com.chisong.green.farm.app.example.PaymentExample;
import com.chisong.green.farm.app.interceptor.PayToOrderInterceptor;
import com.chisong.green.farm.app.miniProgram.enums.PayStatusEnum;
import com.chisong.green.farm.app.miniProgram.request.PayOrderQuery;
import com.chisong.green.farm.app.miniProgram.response.PayOrderQueryResultResponse;
import com.chisong.green.farm.app.miniProgram.service.WxPayService;
import com.chisong.green.farm.app.service.OrderInfoService;
import com.chisong.green.farm.app.service.PaymentService;
import com.chisong.green.farm.app.service.RefundOrderService;
import com.chisong.green.farm.app.service.RefundPaymentService;
import com.chisong.green.farm.app.utils.AESUtil;
import com.github.wxpay.sdk.WXPayUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

	@Autowired
	private RefundOrderService refundOrderService;
	@Autowired
	private RefundPaymentService refundPaymentService;
	@Value("${key}")
	private String key;

	@Autowired
	private PayToOrderInterceptor payToOrderInterceptor;

	/**
	 * 支付成功回调接口
	 */
	@RequestMapping("/payNotify")
	public void payNotify(HttpServletResponse httpServletResponse) {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
			.getRequest();
		httpServletResponse.setHeader("Content-type", "application/xml;charset=UTF-8");
		httpServletResponse.setCharacterEncoding("UTF-8");

		try {
			InputStream inputStream = request.getInputStream();
			//BufferedReader是包装设计模式，性能更搞
			BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			StringBuffer sb = new StringBuffer();
			//1、将微信回调信息转为字符串
			String line;
			while((line = in.readLine()) != null) {
				sb.append(line);
			}
			in.close();
			String xml = sb.toString();
			log.info("payNotify result  {}", xml);
			//2、将xml格式字符串格式转为map集合
			Map<String, String> callbackMap = WXPayUtil.xmlToMap(xml);

			//5、判断回调信息是否成功
			if("SUCCESS".equals(callbackMap.get("result_code"))) {
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
						.andPayNoEqualTo(outTradeNo);

					List<PaymentDto> paymentDtoList = paymentService.getList(paymentExample);
					//更新订单状态
					if(!CollectionUtils.isEmpty(paymentDtoList)) {
						PaymentDto paymentDto = paymentDtoList.get(0);
						if(paymentDto.getStatus() == 1){//状态已经被更新，则无需再次处理
							returnOk(httpServletResponse);
							return;
						}

						paymentDto.setThirdPayNo(payOrderQueryResultResponse.getTransactionId());
						paymentDto.setStatus(1);
						paymentDto.setPaySuccessTime(new Date());
						paymentDto.setPayedAmount(payOrderQueryResultResponse.getTotalFee());
						paymentService.update(paymentDto);

						String orderNo = paymentDto.getPayOrderNo();
						OrderInfoDto orderInfoDto = orderInfoService.getOrderByNo(orderNo);
						orderInfoDto.setStatus(OrderStatusEnum.PAYED.code());
						orderInfoDto.setPayNo(paymentDto.getPayNo());
						orderInfoDto.setPayedAmount(Long.parseLong(payOrderQueryResultResponse.getTotalFee() + ""));
						orderInfoService.update(orderInfoDto);

						//拦截处理
						payToOrderInterceptor.handle(paymentDto.getId());
					}

				}
				returnOk(httpServletResponse);
				return;
			}
		} catch(Exception ex) {
			log.error("支付回调异常:", ex);
			ex.printStackTrace();
		}


	}

	/**
	 * 退款结果通知
	 */
	@RequestMapping("/refundNotify")
	public void refundNotify(HttpServletResponse httpServletResponse) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
			.getRequest();

		httpServletResponse.setHeader("Content-type", "application/xml;charset=UTF-8");
		httpServletResponse.setCharacterEncoding("UTF-8");
		try {
			InputStream inputStream = request.getInputStream();
			//BufferedReader是包装设计模式，性能更搞
			BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			StringBuffer sb = new StringBuffer();
			//1、将微信回调信息转为字符串
			String line;
			while((line = in.readLine()) != null) {
				sb.append(line);
			}
			in.close();
			inputStream.close();
			//2、将xml格式字符串格式转为map集合
			String refundXml =  sb.toString();
			log.info("refundXml == {}", refundXml);
			Map<String, String> callbackMap = WXPayUtil.xmlToMap(refundXml);
			String reqInfoBody = callbackMap.get("req_info");
			String reqInfoXml =  AESUtil.decryptData(reqInfoBody, key);
			callbackMap.putAll( WXPayUtil.xmlToMap(reqInfoXml));
			log.info("refund result == {}", callbackMap);
			/**
			 * 退款状态
			 */
			String refundStatus = callbackMap.get("refund_status");
			log.info("refundStatus == {}", refundStatus);
			if(!"SUCCESS".equals(refundStatus)){//退款未成功，不做逻辑处理
				returnOk(httpServletResponse);
				return;
			}


			/**
			 * 微信交易订单号
			 */
			String transactionId = callbackMap.get("transaction_id");
			/**
			 * 商户订单号
			 */
			String outTradeNo = callbackMap.get("out_trade_no");
			/**
			 * 微信退款单号
			 */
			String refundId =  callbackMap.get("refund_id");
			/**
			 * 商户退款单号
			 */
			String outRefundNo = callbackMap.get("out_refund_no");
			/**
			 * 退款金额
			 */
			String settlementRefundFee =callbackMap.get("settlement_refund_fee") ;
			/**
			 * 退款成功时间
			 */
			String successTime = callbackMap.get("success_time");

			//1.更新退款流水
			RefundPaymentDto refundPaymentDto = refundPaymentService.getRefundPaymentDtoByRefundNo(outRefundNo);
			if(refundPaymentDto.getStatus()==1){
				returnOk(httpServletResponse);
				return;
			}
			refundPaymentDto.setRefundSuccessTime(successTime);
			refundPaymentDto.setStatus(1);
			refundPaymentDto.setOutRefundNo(refundId);
			refundPaymentService.update(refundPaymentDto);


			//2. 更新退款申请单
			RefundOrderDto refundOrderDto =  refundOrderService.getRefundOrderDto(refundPaymentDto.getRefundOrderNo());
			refundOrderDto.setRefundRemark("退款成功");
			refundOrderDto.setStatus(3);
			refundOrderService.update(refundOrderDto);

			//3.更新订单退款金额
			OrderInfoDto orderInfoDto =	orderInfoService.getOrderByNo(refundOrderDto.getOrderNo());
			orderInfoDto.setRefundAmount(orderInfoDto.getRefundAmount()+Integer.parseInt(settlementRefundFee));
			orderInfoDto.setIncome(orderInfoDto.getIncome() - Integer.parseInt(settlementRefundFee));
			PaymentDto paymentDto = paymentService.getPaymentDto(orderInfoDto.getPayNo());
			//未发货的订单，全额退款后，订单取消
			if(orderInfoDto.getRefundAmount() - orderInfoDto.getPayedAmount()==0
			 && orderInfoDto.getStatus() == OrderStatusEnum.PAYED.code()){
				orderInfoDto.setStatus(OrderStatusEnum.CANCELED.code());
			}
			orderInfoService.update(orderInfoDto);
			//退款拦截，退各种费项
			payToOrderInterceptor.refund(paymentDto.getId(), refundOrderDto.getAmount());

			if(orderInfoDto.getRefundAmount() - orderInfoDto.getPayedAmount()==0){
				paymentDto.setStatus(5);
				paymentService.update(paymentDto);
			}
			returnOk(httpServletResponse);
			return;
		} catch(Exception ex) {
			log.error("退款回调异常:", ex);
			ex.printStackTrace();
		}
	}

	private void returnOk(HttpServletResponse httpServletResponse) throws IOException {
		httpServletResponse.getWriter().write("<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
	}
}
