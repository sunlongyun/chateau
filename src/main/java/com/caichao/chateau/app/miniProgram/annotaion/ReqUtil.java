package com.caichao.chateau.app.miniProgram.annotaion;

import com.caichao.chateau.app.miniProgram.request.LoginReq;
import com.caichao.chateau.app.utils.JsonUtils;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;

/**
 * 描述:
 *
 * @AUTHOR 孙龙云
 * @date 2019-06-07 16:33
 */
@Slf4j
public class ReqUtil {

	/**
	 * 获取map对象
	 */
	public static Map<String, Object> getMap(Object reqObj) {
		Map<String, Object> dataMap = new HashMap<>();
		if(null != reqObj) {
			Class clazz = reqObj.getClass();
			Field[] fieldList = clazz.getDeclaredFields();
			for(Field field : fieldList) {
				field.setAccessible(true);
				String fieldName = field.getName();
				MiniFiled miniFiled = field.getAnnotation(MiniFiled.class);
				if(null != miniFiled) {
					fieldName = miniFiled.value();
				}
				Object value = ReflectionUtils.getField(field, reqObj);
				field.setAccessible(false);
				dataMap.put(fieldName, value);
			}
		}
		return dataMap;
	}

	/**
	 * 获取json数据
	 */
	public static String getJson(Object reqObj) {
		Map<String, Object> dataMap = getMap(reqObj);
		return JsonUtils.object2JsonString(dataMap);
	}

	/**
	 * 获取字符串用&隔开
	 * @param reqObj
	 * @return
	 */
	public static String getStr(Object reqObj){
		Map<String, Object> dataMap = getMap(reqObj);
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("?");
		Iterator<String> it = dataMap.keySet().iterator();
		int i=0;
		while(it.hasNext()){
			String key = it.next();
			Object value = dataMap.get(key);
			if(null != value){
				if(i>0){
					stringBuilder.append("&");
				}
				stringBuilder.append(key).append("=").append(value);
				i++;
			}

		}
		return stringBuilder.toString();
	}

}
