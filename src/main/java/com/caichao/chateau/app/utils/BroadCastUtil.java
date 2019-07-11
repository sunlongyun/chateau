package com.caichao.chateau.app.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

/**
 * 描述:
 * 直播工具类
 * @AUTHOR 孙龙云
 * @date 2019-07-11 21:51
 */
public class BroadCastUtil {


	/**
	 * 获取推送流地址
	 * @param pusherPrefix
	 * @param bizId
	 * @param roomId
	 * @return
	 */
	public static String getPusherUrl(String pusherPrefix, String bizId, String roomId){
		String key = roomId;
		String streamName = getSafeUrl(key,roomId, LocalDateTime.now().plus(1, ChronoUnit.DAYS).getNano() );
		String url = pusherPrefix+roomId+"?bizid="+bizId+streamName;
		return url;
	}


	private static final char[] DIGITS_LOWER =
		{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

	/*
	 * KEY+ streamName + txTime
	 */
	private static String getSafeUrl(String key, String streamName, long txTime) {
		String input = new StringBuilder().
			append(key).
			append(streamName).
			append(Long.toHexString(txTime).toUpperCase()).toString();

		String txSecret = null;
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			txSecret  = byteArrayToHexString(
				messageDigest.digest(input.getBytes("UTF-8")));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return txSecret == null ? "" :
			new StringBuilder().
				append("txSecret=").
				append(txSecret).
				append("&").
				append("txTime=").
				append(Long.toHexString(txTime).toUpperCase()).
				toString();
	}

	private static String byteArrayToHexString(byte[] data) {
		char[] out = new char[data.length << 1];

		for (int i = 0, j = 0; i < data.length; i++) {
			out[j++] = DIGITS_LOWER[(0xF0 & data[i]) >>> 4];
			out[j++] = DIGITS_LOWER[0x0F & data[i]];
		}
		return new String(out);
	}
}
