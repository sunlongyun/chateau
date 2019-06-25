package com.caichao.chateau.app.wpay.util;

import com.github.wxpay.sdk.IWXPayDomain;
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

	/**
	 * 获取支付sdk
	 * @return
	 */
	public static WXPay getWXPay(String notifyUrl) {
		//final WXPayConfig config, final String notifyUrl, final boolean autoReport, final boolean useSandbox
		WXPayConfig wxPayConfig = getWXPayConfig();
		WXPay wxPay = null;
		try {
			wxPay = new WXPay(wxPayConfig, notifyUrl, false, false);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return wxPay;
	}

	private static WXPayConfig getWXPayConfig(){
		WXPayConfig wxPayConfig = new CCWxPayConfig();
		return  wxPayConfig;
	}



}
