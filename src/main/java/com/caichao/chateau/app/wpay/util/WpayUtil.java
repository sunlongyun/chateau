package com.caichao.chateau.app.wpay.util;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import java.io.InputStream;
import lombok.extern.slf4j.Slf4j;

/**
 * 描述:
 *
 * @AUTHOR 孙龙云
 * @date 2019-06-13 21:16
 */
@Slf4j
public class WpayUtil {
	private  static WXPay wxPay = null;
	/**
	 * 获取支付sdk
	 */
	public static WXPay getWXPay(String merchId, String appId, String key, InputStream certStream, String notifyUrl) {
		if(null == wxPay){
			synchronized(WpayUtil.class){
				if(null == wxPay){
					WXPayConfig wxPayConfig = getWXPayConfig(merchId, appId, key, certStream);
					try {
						wxPay = new WXPay(wxPayConfig, notifyUrl, false, false);
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return wxPay;

	}

	private static WXPayConfig getWXPayConfig(String merchId, String appId, String key, InputStream certStream) {
		WXPayConfig wxPayConfig = new CCWxPayConfig(merchId, appId, key, certStream);
		return wxPayConfig;
	}

}
