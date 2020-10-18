package com.chisong.green.farm.app.utils;

/**
 * 描述:
 *
 * @AUTHOR 孙龙云
 * @date 2020-10-02 14:26
 */
public class AppUtils {
	private static ThreadLocal<Long> appIdThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> appIdNameThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> appIdSecretThreadLocal = new ThreadLocal<>();

	public static void set(Long appId){
		appIdThreadLocal.set(appId);
	}

	public static Long get(){
		return appIdThreadLocal.get();
	}

	public static void remove(){
		appIdThreadLocal.remove();
	}

	public static void setName(String appId){
		appIdNameThreadLocal.set(appId);
	}

	public static String getName(){
		return appIdNameThreadLocal.get();
	}

	public static void removeName(){
		appIdNameThreadLocal.remove();
	}

	public static void setSecret(String secret){
		appIdSecretThreadLocal.set(secret);
	}

	public static String getSecret(){
		return appIdSecretThreadLocal.get();
	}

	public static void removeSecret(){
		appIdSecretThreadLocal.remove();
	}
}
