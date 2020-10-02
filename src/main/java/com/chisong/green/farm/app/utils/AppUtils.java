package com.chisong.green.farm.app.utils;

/**
 * 描述:
 *
 * @AUTHOR 孙龙云
 * @date 2020-10-02 14:26
 */
public class AppUtils {
	private static ThreadLocal<Long> appIdThreadLocal = new ThreadLocal<>();

	public static void set(Long appId){
		appIdThreadLocal.set(appId);
	}

	public static Long get(){
		return appIdThreadLocal.get();
	}

	public static void remove(){
		appIdThreadLocal.remove();
	}
}
