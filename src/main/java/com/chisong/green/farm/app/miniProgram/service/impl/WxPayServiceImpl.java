package com.chisong.green.farm.app.miniProgram.service.impl;

import com.chisong.green.farm.app.miniProgram.MapXmlUtil;
import com.chisong.green.farm.app.miniProgram.ReqUtil;
import com.chisong.green.farm.app.miniProgram.ResUtil;
import com.chisong.green.farm.app.miniProgram.request.PayOrderQuery;
import com.chisong.green.farm.app.miniProgram.request.PayToPersonRequest;
import com.chisong.green.farm.app.miniProgram.request.PrePayRequest;
import com.chisong.green.farm.app.miniProgram.request.RedBagRequest;
import com.chisong.green.farm.app.miniProgram.request.RefundApplyReq;
import com.chisong.green.farm.app.miniProgram.request.SendCardRequest;
import com.chisong.green.farm.app.miniProgram.response.ParentResponse;
import com.chisong.green.farm.app.miniProgram.response.PayOrderQueryResultResponse;
import com.chisong.green.farm.app.miniProgram.response.PayToPersonResponse;
import com.chisong.green.farm.app.miniProgram.response.PrePayResponse;
import com.chisong.green.farm.app.miniProgram.response.RedBagResponse;
import com.chisong.green.farm.app.miniProgram.response.SendCardResponse;
import com.chisong.green.farm.app.miniProgram.service.WxPayService;
import com.chisong.green.farm.app.utils.AppUtils;
import com.chisong.green.farm.app.wpay.util.WpayUtil;
import com.github.wxpay.sdk.WXPay;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 描述: 微信支付
 *
 * @AUTHOR 孙龙云
 * @date 2019-06-28 22:06
 */
@Service
@Slf4j
public class WxPayServiceImpl implements WxPayService {

	/**
	 * 商户号
	 */
	@Value("${mchID}")
	private String mchID;
//	@Value("${appid}")
//	private String appid;
//	@Value("${secret}")
//	private String secret;
	@Value("${notifyUrl}")
	private String notifyUrl;
	/**
	 * 退款结果通知url、
	 */
	@Value("${refundNotifyUrl}")
	private String refundNotifyUrl;

	@Value("${key}")
	private String key;

	@Value("${crt_path}")
	private String cerPath;
	/**
	 * 企业向个人转账url
	 */
	private String payToPersonUrl = "/mmpaymkttransfers/promotion/transfers";

	/**
	 * 获取微信sdk
	 */
	private WXPay getWxPay() {
		try {
			String certPath = cerPath + "/apiclient_cert.p12";
			File file = new File(certPath);
			InputStream certStream = new FileInputStream(file);
			byte[] bytes = new byte[certStream.available()];
			certStream.read(bytes);
			WXPay wxPay = WpayUtil.getWXPay(mchID, AppUtils.getName(), key, bytes, notifyUrl);
			certStream.close();
			return wxPay;
		} catch(Exception ex) {
			ex.printStackTrace();
			log.error("获取WXPay失败:", ex);
			throw new RuntimeException("获取WXPay失败");
		}

	}

	@Override
	public PrePayResponse prePay(PrePayRequest prePayRequest) {
		Map<String, String> dataMap = ReqUtil.getMap(prePayRequest);
		WXPay wxPay = getWxPay();
		try {
			Map<String, String> resultMap = wxPay.unifiedOrder(dataMap);
			Map<String, Object> targetMap = new HashMap<>();
			targetMap.putAll(resultMap);
			log.info("响应结果:{}", resultMap);
			PrePayResponse prePayResponse = ResUtil.getObj(PrePayResponse.class, targetMap);

			String prePayId = "prepay_id=" + prePayResponse.getPrepayId();
			Map<String, String> payMap = new HashMap<>();
			payMap.put("package", prePayId);
			payMap.put("time_stamp", (System.currentTimeMillis() / 1000) + "");

			Map<String, String> payParam = wxPay.fillRequestData(payMap);
			String nonStr = payParam.get("nonce_str");
			String packageStr = payParam.get("package");
			String timeStamp = payParam.get("time_stamp");
			String appId = payParam.get("appid");
			String signType = payParam.get("sign_type");
			String sign = payParam.get("sign");
			prePayResponse.setNonceStr(nonStr);
			prePayResponse.setPackageStr(packageStr);
			prePayResponse.setTimeStamp(timeStamp);
			prePayResponse.setAppId(appId);
			prePayResponse.setSignType(signType);
			prePayResponse.setSign(sign);

			return prePayResponse;
		} catch(Exception ex) {
			log.error("支付接口调用失败:", ex);
			throw new RuntimeException("微信支付失败:" + ex.getCause());
		}
	}

	@Override
	public PayOrderQueryResultResponse queryPayOrder(PayOrderQuery payOrderQuery) {
		Map<String, String> dataMap = ReqUtil.getMap(payOrderQuery);
		WXPay wxPay = getWxPay();
		try {
			Map<String, String> resultMap = wxPay.orderQuery(dataMap);
			Map<String, Object> targetMap = new HashMap<>();
			targetMap.putAll(resultMap);

			PayOrderQueryResultResponse payOrderQueryResultResponse = ResUtil.getObj(PayOrderQueryResultResponse
				.class, targetMap);
			return payOrderQueryResultResponse;
		} catch(Exception ex) {
			log.error("支付查询接口调用失败:", ex);
			throw new RuntimeException("微信支付查询异常:" + ex.getCause());
		}
	}

	@Override
	public ParentResponse refundOrder(RefundApplyReq refundApplyReq) {
		refundApplyReq.setNotifyUrl(refundNotifyUrl);
		Map<String, String> dataMap = ReqUtil.getMap(refundApplyReq);
		WXPay wxPay = getWxPay();

		try {
			Map<String, String> resultMap = wxPay.refund(dataMap);
			Map<String, Object> targetMap = new HashMap<>();
			targetMap.putAll(resultMap);

			ParentResponse parentResponse = ResUtil.getObj(ParentResponse.class, targetMap);
			return parentResponse;
		} catch(Exception ex) {
			log.error("支付退款接口调用失败:", ex);
			throw new RuntimeException("微信支付查询异常:" + ex.getCause());
		}
	}

	@Override
	public RedBagResponse RedBagResponse(RedBagRequest redBagRequest) {
		Map<String, String> dataMap = ReqUtil.getMap(redBagRequest);
		log.info("dataMap == {}", dataMap);
		WXPay wxPay = getWxPay();
		try {
			dataMap = wxPay.fillRequestData(dataMap);
		} catch(Exception ex) {
			log.info("红包发送失败, {}", ex);
		}

		return null;
	}

	@Override
	public PayToPersonResponse payToPerson(PayToPersonRequest payToPersonRequest) {
		payToPersonRequest.setMerchantId(mchID);
		payToPersonRequest.setMerchantAppid(AppUtils.getName());
//		payToPersonRequest.setIp(IPUtil.getIpAddr());

		Map<String, String> reqMap = ReqUtil.getMap(payToPersonRequest);
		WXPay wxPay = getWxPay();
		try {
			log.info("入参:{}", reqMap);
			String resultBody = wxPay.requestWithCert(payToPersonUrl, wxPay.fillRequestData(reqMap), 5000, 5000);
			Map resultMap =  MapXmlUtil.xmlToMap(resultBody);
			log.info("响应结果:{}", resultMap);
			PayToPersonResponse payToPersonResponse = ResUtil.getObj(PayToPersonResponse.class, resultMap);
			return  payToPersonResponse;
		} catch(Exception ex) {
			log.info("付款失败 == {}", ex);
		}
		return null;
	}


	@Override
	public SendCardResponse sendCard(SendCardRequest sendCardRequest) {
//		sendCardRequest.setMerchantId(mchID);
//		sendCardRequest.setMerchantAppid(appid);

		int random = (int)(Math.random()*10000);

		String partnerTradeNo =
			mchID+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))+addZeroForNum(random+"", 4);
		sendCardRequest.setPartnerTradeNo(partnerTradeNo);
		Map<String, String> reqMap = ReqUtil.getMap(sendCardRequest);
		WXPay wxPay = getWxPay();


		try {
			reqMap = wxPay.fillRequestData(reqMap);
		String resultBody =	wxPay.requestWithCert("/mmpaymkttransfers/send_coupon",reqMap,
				5000,5000);
			Map resultMap =  MapXmlUtil.xmlToMap(resultBody);
			SendCardResponse sendCardResponse =  ResUtil.getObj(SendCardResponse.class, resultMap);
			return sendCardResponse;
		}catch(Exception ex){
			log.info("发送购物券失败 == {}", ex);
		}

		return null;
	}

	private  String addZeroForNum(String str, int strLength) {
		int strLen = str.length();
		if(strLen < strLength) {
			while(strLen < strLength) {
				StringBuffer sb = new StringBuffer();
				sb.append("0").append(str);//左补0
				str = sb.toString();
				strLen = str.length();
			}
		}
		return str;
	}
}
