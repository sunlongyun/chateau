package com.chisong.green.farm.app.controller.index;

import com.chisong.green.farm.app.dto.MerchantPaymentDto;
import com.chisong.green.farm.app.example.ProvinceCityAreaExample;
import com.chisong.green.farm.app.miniProgram.ResUtil;
import com.chisong.green.farm.app.miniProgram.msg.ReceiveMsg;
import com.chisong.green.farm.app.miniProgram.msg.TextMsg;
import com.chisong.green.farm.app.service.AuthBizService;
import com.chisong.green.farm.app.service.CustomerCenterService;
import com.chisong.green.farm.app.service.MerchantPaymentService;
import com.chisong.green.farm.app.service.ProvinceCityAreaService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 描述:
 *
 * @AUTHOR 孙龙云
 * @date 2020-02-29 16:37
 */
@RestController
@Slf4j
public class IndexController {

	@Autowired
	private CustomerCenterService customerCenterService;

	@Autowired
	private ProvinceCityAreaService provinceCityAreaService;
	@Autowired
	private MerchantPaymentService merchantPaymentService;

	@RequestMapping("/hello")
	public String hello(){
		MerchantPaymentDto merchantPaymentDto = new MerchantPaymentDto();
		merchantPaymentDto.setOpenId("11");
		merchantPaymentDto.setPayType(0);
		merchantPaymentDto.setStatus(0);
		merchantPaymentDto.setTradeNo("hello world");
		merchantPaymentDto.setRemark("hello");
		merchantPaymentService.save(merchantPaymentDto);
		return "hello";
	}

	@GetMapping("/")
	private String index(String signature,String timestamp, String nonce, String echostr){
		log.info("signature:{},timestamp:{},nonce:{},echostr:{}", signature, timestamp, nonce, echostr);
	return echostr;
	}
	/**
	 * 获取POST请求中Body参数
	 * @param request
	 * @return 字符串
	 */
	public String getParm(HttpServletRequest request) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
		} catch(IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String line = null;
		StringBuilder sb = new StringBuilder();
		try {
			while((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch(IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}
	/**
	 * 接收信息
	 * @return
	 */
	@PostMapping("/")
	private String receive(){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
			.getRequest();
	    String body = 	getParm(request);
	    log.info("body=={}", body);
	    ReceiveMsg receiveMsg = ResUtil.getObj(ReceiveMsg.class, body);

		log.info("receiveMsg:{}", receiveMsg);
		 receiveMsg.getFromUserName();

		log.info("msgId:{}", receiveMsg.getMsgId());
		return "success";
	}
	/**
	 * 回复
	 * @return
	 */
	@RequestMapping("/answer")
	public String answer(){
		TextMsg textMsg = new TextMsg();
		TextMsg.Content content = 	new TextMsg.Content();
		content.setContent("你好,很高兴认识你");
		textMsg.setText(content);
		textMsg.setTouser("oqrTq4jLQt0I_9F4vQVQLQGDrBbM");
		customerCenterService.sendToCustomer(textMsg);
		return "ok";
	}
}
