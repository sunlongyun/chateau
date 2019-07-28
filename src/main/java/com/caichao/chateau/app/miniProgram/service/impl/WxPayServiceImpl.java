package com.caichao.chateau.app.miniProgram.service.impl;

import com.caichao.chateau.app.miniProgram.annotaion.ReqUtil;
import com.caichao.chateau.app.miniProgram.annotaion.ResUtil;
import com.caichao.chateau.app.miniProgram.request.PayOrderQuery;
import com.caichao.chateau.app.miniProgram.request.PrePayRequest;
import com.caichao.chateau.app.miniProgram.response.PayOrderQueryResultResponse;
import com.caichao.chateau.app.miniProgram.response.PrePayResponse;
import com.caichao.chateau.app.miniProgram.service.WxPayService;
import com.caichao.chateau.app.wpay.util.WpayUtil;
import com.github.wxpay.sdk.WXPay;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 描述:
 * 微信支付
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
	@Value("${appid}")
	private String appid;
	@Value("${secret}")
	private String secret;
	@Value("${notifyUrl}")
	private String notifyUrl;
	@Value("${key}")
	private String key;

	@Value("${crt_path}")
	private String cerPath;
	/**
	 * 获取微信sdk
	 */
	private WXPay getWxPay() {
		try {
			String certPath = cerPath+"/apiclient_cert.p12";
			File file = new File(certPath);
			InputStream certStream = new FileInputStream(file);
			byte[] bytes  = new byte[(int) file.length()];
			certStream.read(bytes);
			WXPay wxPay =  WpayUtil.getWXPay(mchID, appid, key, null, notifyUrl);
			certStream.close();
			return  wxPay;
		}catch(Exception ex){
			ex.printStackTrace();
			log.error("获取WXPay失败:",ex);
			throw new RuntimeException("获取WXPay失败");
		}

	}

	@Override
	public PrePayResponse prePay(PrePayRequest prePayRequest) {
		Map<String, String> dataMap = ReqUtil.getMap(prePayRequest);
		WXPay wxPay = getWxPay();
		try {
			Map<String, String> resultMap = wxPay.unifiedOrder(dataMap);
			log.info("响应结果:{}", resultMap);
			PrePayResponse prePayResponse = ResUtil.getObj(PrePayResponse.class, resultMap);

			String prePayId = "prepay_id=" + prePayResponse.getPrepayId();
			Map<String, String> payMap = new HashMap<>();
			payMap.put("package", prePayId);
			payMap.put("time_stamp", (System.currentTimeMillis() / 1000) + "");

			Map<String, String> payParam = wxPay.fillRequestData(payMap);
			String nonStr = payParam.get("nonce_str");
			String packageStr = payParam.get("package");
			String timeStamp  =payParam.get("time_stamp");
			String appId =  payParam.get("appid");
			String signType = payParam.get("sign_type");
			String sign =payParam.get("sign");
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
			PayOrderQueryResultResponse payOrderQueryResultResponse = ResUtil.getObj(PayOrderQueryResultResponse
				.class, resultMap);
			return payOrderQueryResultResponse;
		} catch(Exception ex) {
			log.error("支付查询接口调用失败:", ex);
			throw new RuntimeException("微信支付查询异常:" + ex.getCause());
		}
	}
}
