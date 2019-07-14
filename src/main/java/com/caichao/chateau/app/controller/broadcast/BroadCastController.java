package com.caichao.chateau.app.controller.broadcast;

import com.caichao.chateau.app.dto.CountryChateauDto;
import com.caichao.chateau.app.service.CountryChateauService;
import com.caichao.chateau.app.utils.JsonUtils;
import com.github.wxpay.sdk.WXPayUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 描述:
 * 直播管理controller
 *
 * @AUTHOR 孙龙云
 * @date 2019-07-12 21:13
 */
@RequestMapping("broadCast")
@RestController
@Slf4j
public class BroadCastController {

	@Autowired
	private CountryChateauService countryChateauService;

	/**
	 * 推流回调地址
	 */
	@RequestMapping("startPush")
	public void startPush() {
		HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
			.getRequest();
		HttpServletResponse httpServletResponse = ((ServletRequestAttributes) RequestContextHolder
			.getRequestAttributes())
			.getResponse();
		httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
		httpServletResponse.setCharacterEncoding("UTF-8");

		CallbackReq callbackReq = getCallbackReq(httpServletRequest);
		log.info("callbackReq:{}", callbackReq);

		String streamId = callbackReq.getStream_id();
		String[] sts = streamId.split("_");
		log.info("sts:{}", sts);
		String broadcastType = sts[0];
		String chateauCode = sts[1];

		CountryChateauDto countryChateauDto = countryChateauService.getByCode(chateauCode);
		if(null != countryChateauDto){
			if("daily".equals(broadcastType)){
				countryChateauDto.setDailyBroadcastIng(1);
			}else{
				countryChateauDto.setMasterBroadcastIng(1);
			}
			countryChateauService.update(countryChateauDto);
		}

		returnRes(httpServletResponse);
	}

	/**
	 * 获取回调对象
	 */
	private CallbackReq getCallbackReq(HttpServletRequest httpServletRequest) {
		String content = readBodyByRequest(httpServletRequest);
		log.info("content:{}", content);
		CallbackReq callbackReq = JsonUtils.json2Object(content, CallbackReq.class);

		return callbackReq;
	}

	/**
	 * 断流回调地址
	 */
	@RequestMapping("endPush")
	public void endPushPush() {
		HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
			.getRequest();
		HttpServletResponse httpServletResponse = ((ServletRequestAttributes) RequestContextHolder
			.getRequestAttributes())
			.getResponse();
		httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
		httpServletResponse.setCharacterEncoding("UTF-8");

		CallbackReq callbackReq = getCallbackReq(httpServletRequest);
		String streamId = callbackReq.getStream_id();
		String[] sts = streamId.split("_");
		String broadcastType = sts[0];
		String chateauCode = sts[1];

		CountryChateauDto countryChateauDto = countryChateauService.getByCode(chateauCode);
		if(null != countryChateauDto){
			if("daily".equals(broadcastType)){
				countryChateauDto.setDailyBroadcastIng(0);
			}else{
				countryChateauDto.setMasterBroadcastIng(0);
			}
			countryChateauService.update(countryChateauDto);
		}

		returnRes(httpServletResponse);
	}

	private void returnRes(HttpServletResponse httpServletResponse) {
		try {

			Map<String, String> resultMap = new HashMap<>();
			resultMap.put("return_code", "SUCCESS");
			resultMap.put("return_msg", "OK");
			String reqBody = WXPayUtil.mapToXml(resultMap);
			httpServletResponse.getWriter().write(reqBody);

		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}


	// 字符串读取
	// 方法一
	public String readBodyByRequest(HttpServletRequest request) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder("");
		try {
			br = request.getReader();
			String str;
			while((str = br.readLine()) != null) {
				sb.append(str);
			}
			br.close();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(null != br) {
				try {
					br.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}
}
