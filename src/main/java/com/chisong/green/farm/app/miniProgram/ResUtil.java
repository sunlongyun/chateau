package com.chisong.green.farm.app.miniProgram;

import com.chisong.green.farm.app.miniProgram.annotaion.MiniFiled;
import com.chisong.green.farm.app.miniProgram.response.ParentResponse;
import com.chisong.green.farm.app.miniProgram.response.PayToPersonResponse;
import com.chisong.green.farm.app.utils.JsonUtils;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

/**
 * 描述:
 *
 * @AUTHOR 孙龙云
 * @date 2019-06-07 17:13
 */
@Slf4j
public class ResUtil {

	/**
	 * 从对象获取json
	 * @param obj
	 * @return
	 */
	public static  String getJson(Object obj){
		if(null == obj){
			return "{}";
		}

		Class clazz =	obj.getClass();
		List<Field> fieldList = new ArrayList<>();
		fieldList.addAll(Arrays.asList(clazz.getDeclaredFields()));
		if(null != clazz.getSuperclass()){
			fieldList.addAll(Arrays.asList(clazz.getSuperclass().getDeclaredFields()));
		}
		Map<String, Object> targetMap = new HashMap<>();
		for(Field field : fieldList){
			String keyName = field.getName();
			field.setAccessible(true);
			MiniFiled miniFiled = field.getAnnotation(MiniFiled.class);
			if(null != miniFiled){
				keyName = miniFiled.value();
			}
			try {
				Object value = field.get(obj);
				targetMap.put(keyName, value);
			}catch(Exception ex){

			}

			field.setAccessible(false);
		}
		return JsonUtils.object2JsonString(targetMap);
	}

	/**
	 * 获取对象
	 */
	public static <T> T getObj(Class<T> clazz, Map<String, Object> dataMap) {
		Object obj = null;
		try {
			obj = clazz.newInstance();
			List<Field> fieldList = new ArrayList<>();
			fieldList.addAll(Arrays.asList(clazz.getDeclaredFields()));
			if(null != clazz.getSuperclass()){
				fieldList.addAll(Arrays.asList(clazz.getSuperclass().getDeclaredFields()));
			}

			if(obj instanceof ParentResponse) {
				fieldList.addAll(Arrays.asList(ParentResponse.class.getDeclaredFields()));
			}

			for(Field field : fieldList) {
				field.setAccessible(true);
				String filedName = field.getName();
				MiniFiled miniFiled = field.getAnnotation(MiniFiled.class);
				if(null != miniFiled) {
					filedName = miniFiled.value();
				}
				Object value = dataMap.get(filedName);
				if(null == value){
					field.setAccessible(false);
					continue;
				}
				//不同类型处理
				if(field.getType() == Integer.class){
					ReflectionUtils.setField(field, obj, Integer.valueOf(value+"") );
				}else if(field.getType() == Long.class){
					ReflectionUtils.setField(field, obj, Long.valueOf(value+""));
				}else if(field.getType() == Double.class){
					ReflectionUtils.setField(field, obj, Double.valueOf(value+""));
				}else{
					ReflectionUtils.setField(field, obj, value);
				}

				field.setAccessible(false);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}

		return (T) obj;
	}

	/**
	 * 获取对象
	 */
	public static <T> T getObj(Class<T> clazz, String json) {
		if(StringUtils.isEmpty(json)) {
			return null;
		}
		Map<String, Object> dataMap = JsonUtils.json2Object(json, Map.class);

		return getObj(clazz, dataMap);
	}



}
