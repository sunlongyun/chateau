package com.caichao.chateau.app.wpay.util;

import com.github.wxpay.sdk.IWXPayDomain;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayConstants;
import java.io.InputStream;

/**
 * 描述:
 *
 * @AUTHOR 孙龙云
 * @date 2019-06-13 21:35
 */
public class CCWxPayConfig extends WXPayConfig{

	@Override
	public String getMchID() {
		return "1539835521";
	}

	@Override
	public String getAppID() {
		return "wxac4c162ada26ae0e";
	}


	@Override
	public String getKey() {
		return "sunlongyun1478529630sunlongyun12";
	}

	@Override
	public InputStream getCertStream() {
		return null;
	}

	@Override
	public IWXPayDomain getWXPayDomain() {
		// 这个方法需要这样实现, 否则无法正常初始化WXPay
		IWXPayDomain iwxPayDomain = new IWXPayDomain() {

			public void report(String domain, long elapsedTimeMillis, Exception ex) {

			}

			public DomainInfo getDomain(WXPayConfig config) {
				return new IWXPayDomain.DomainInfo(WXPayConstants.DOMAIN_API, true);
			}
		};
		return iwxPayDomain;

	}


}
