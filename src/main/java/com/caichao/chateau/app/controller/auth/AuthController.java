package com.caichao.chateau.app.controller.auth;

import com.caichao.chateau.app.controller.response.CCResponse;
import com.caichao.chateau.app.service.AuthBizService;
import java.util.HashMap;
import java.util.Map;
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
public class AuthController {

	@Autowired
	private AuthBizService authBizService;

	/**
	 * 登录接口
	 */
	@RequestMapping("/login")
	public CCResponse login(String jsCode) {
		String userCode = authBizService.login(jsCode);
		return CCResponse.success(userCode);
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
