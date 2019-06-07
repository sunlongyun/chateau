package com.caichao.chateau.app.miniProgram.annotaion;

import com.caichao.chateau.app.miniProgram.request.LoginReq;
import com.caichao.chateau.app.utils.JsonUtils;
import java.lang.reflect.Field;
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
	 * 获取对象
	 */
	public static <T> T getObj(Class<T> clazz, Map<String, Object> dataMap) {
		Object obj = null;
		try {
			obj = clazz.newInstance();
			Field[] fields = clazz.getDeclaredFields();
			for(Field field : fields) {
				field.setAccessible(true);
				String filedName = field.getName();
				MiniFiled miniFiled = field.getAnnotation(MiniFiled.class);
				if(null != miniFiled) {
					filedName = miniFiled.value();
				}
				Object value = dataMap.get(filedName);
				ReflectionUtils.setField(field, obj, value);
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
