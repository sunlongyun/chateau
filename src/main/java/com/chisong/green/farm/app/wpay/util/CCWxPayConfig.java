package com.chisong.green.farm.app.wpay.util;

import com.github.wxpay.sdk.IWXPayDomain;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayConstants;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * 描述:
 *
 * @AUTHOR 孙龙云
 * @date 2019-06-13 21:35
 */
public class CCWxPayConfig extends WXPayConfig{

	private String mchId;
	private String appId;
	private String key;
	private byte[] certData;
	private  InputStream certStream;

	public CCWxPayConfig(String mchId, String appId, String key, byte[] certData) {
		this.mchId = mchId;
		this.appId = appId;
		this.key = key;
		this.certData = certData;
	}

	@Override
	public String getMchID() {
		return this.mchId;
	}

	@Override
	public String getAppID() {
		return this.appId;
	}


	@Override
	public String getKey() {
		return this.key;
	}

	@Override
	public InputStream getCertStream() {
		ByteArrayInputStream certBis = new     ByteArrayInputStream(this.certData);
		return certBis;
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
