package com.chisong.green.farm.app.controller.auth;

import com.chisong.green.farm.app.controller.response.CCResponse;
import com.chisong.green.farm.app.miniProgram.response.LoginResponse;
import com.chisong.green.farm.app.service.AuthBizService;
import com.chisong.green.farm.app.utils.LoginUserInfoUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 描述:
 *
 * @AUTHOR 孙龙云
 * @date 2019-06-07 19:13
 */
@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

	@Autowired
	private AuthBizService authBizService;

	/**
	 * 获取联系人信息
	 * @return
	 */
	@RequestMapping("/getContacts")
	public CCResponse getContacts(){
		Map<String, Object> contact = new HashMap<>();
		Map<String, Object> contact1 = new HashMap<>();

		contact.put("contactName","小沈");
		contact.put("mobile", "13402***253");

		contact1.put("contactName","小朱");
		contact1.put("mobile", "19821***781");

		List<Map<String, Object>> list  = new ArrayList<>();
		list.add(contact);
		list.add(contact1);

		Map<String, Object> contactMap = new HashMap<>();
		contactMap.put("contactList", list);

		return CCResponse.success(contactMap);
	}
	/**
	 * 登录接口
	 */
	@RequestMapping("/login")
	public CCResponse login(String code) {
		log.info("登录开始:{}",code);
		String userCode = authBizService.login(code);
		LoginResponse loginResponse = LoginUserInfoUtil.getLoginResponse(userCode);
		Map<String,Object> userMap = new HashMap<>();
		userMap.put("userCode",userCode);
		userMap.put("openId",loginResponse.getOpenid());

		return CCResponse.success(userMap);
	}

	/**
	 * 获取accessToken
	 */
	@RequestMapping("/getAccessToken")
	public CCResponse getAccessToken() {

		Map<String, Object> objectMap = new HashMap<>();
		objectMap.put("accessToken", authBizService.getAccessToken());

		return CCResponse.success(objectMap);
	}
}
