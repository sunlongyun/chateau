package com.chisong.green.farm.app.utils;

/**
 * 描述:
 *
 * @AUTHOR 孙龙云
 * @date 2020-02-16 10:47
 */
public class BitUtil {

	/**
	 * 在数字的某位设置为0
	 * @param value
	 * @param index
	 * @return
	 */
	public static int setZeroAtIndex(int value, int index){

		if(index <= 0){
			throw new RuntimeException("数字和位置必须都是正数");
		}

		return value & ~(1<<(index -1));
	}

	/**
	 * 在数字的某位设置为1
	 * @param value
	 * @param index
	 * @return
	 */
	public static int setOneAtIndex(int value, int index){

		if(index <= 0){
			throw new RuntimeException("数字和位置必须都是正数");
		}


		return value | (1<<(index -1));
	}

	/**
	 * 获取值
	 * @param value
	 * @param index
	 * @return
	 */
	public static  int getBit(int value, int index){
		return (value >> (index-1)) & 1;
	}

}
