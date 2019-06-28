package com.caichao.chateau.app.miniProgram.service.impl;

import com.caichao.chateau.app.miniProgram.annotaion.ReqUtil;
import com.caichao.chateau.app.miniProgram.annotaion.ResUtil;
import com.caichao.chateau.app.miniProgram.request.PrePayRequest;
import com.caichao.chateau.app.miniProgram.response.PrePayResponse;
import com.caichao.chateau.app.miniProgram.service.WxPayService;
import com.caichao.chateau.app.wpay.util.WpayUtil;
import com.github.wxpay.sdk.WXPay;
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

	@Override
	public PrePayResponse prePay(PrePayRequest prePayRequest) {
		Map<String, String> dataMap = ReqUtil.getMap(prePayRequest);
		WXPay wxPay = WpayUtil.getWXPay(mchID, appid, key, null, notifyUrl);
		try {
			Map<String, String> resultMap = wxPay.unifiedOrder(dataMap);
			log.info("响应结果:{}",resultMap);
			PrePayResponse prePayResponse = ResUtil.getObj(PrePayResponse.class, resultMap);
			return  prePayResponse;
		} catch(Exception ex) {
			log.error("支付接口调用失败:", ex);
			throw new RuntimeException("微信支付失败:" + ex.getCause());
		}
	}
}
