package com.caichao.chateau.app.controller.callback;

import com.caichao.chateau.app.controller.callback.request.NotifyRequest;
import com.github.wxpay.sdk.WXPayUtil;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 描述:
 *
 * @AUTHOR 孙龙云
 * @date 2019-06-29 18:18
 */
@RequestMapping("callback")
@Controller
@Slf4j
public class PayCallBackController {

	/**
	 * 支付成功回调接口
	 */
	@RequestMapping("/payNotify")
	public void payNotify(NotifyRequest notifyRequest, HttpServletRequest httpServletRequest, HttpServletResponse
		httpServletResponse) {
		log.info("支付通知结果:{}", notifyRequest);
		httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
		httpServletResponse.setCharacterEncoding("UTF-8");

		Map<String, String> resultMap = new HashMap<>();
		resultMap.put("return_code", "SUCCESS");
		resultMap.put("return_msg", "OK");
		try {
			String reqBody = WXPayUtil.mapToXml(resultMap);
			httpServletResponse.getWriter().write(reqBody);
		} catch(Exception ex) {
			log.error("回调失败:", ex);
		}

	}
}
