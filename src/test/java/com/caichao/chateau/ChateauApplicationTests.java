package com.caichao.chateau;

import com.caichao.chateau.app.constants.enums.Validity;
import com.caichao.chateau.app.dto.CountryDto;
import com.caichao.chateau.app.example.CountryExample;
import com.caichao.chateau.app.miniProgram.request.AcccessCodeReq;
import com.caichao.chateau.app.miniProgram.request.LoginReq;
import com.caichao.chateau.app.miniProgram.response.AccessCodeResponse;
import com.caichao.chateau.app.miniProgram.response.LoginResponse;
import com.caichao.chateau.app.miniProgram.service.AuthService;
import com.caichao.chateau.app.service.CountryService;
import com.caichao.chateau.app.service.CustomerInfoService;
import com.caichao.chateau.app.wpay.util.WpayUtil;
import com.github.wxpay.sdk.WXPay;
import com.lianshang.generator.commons.GenerateFileTypeEnum;
import com.lianshang.utils.LsCodeGeneratorUtil;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class ChateauApplicationTests {

	@Autowired
	private CustomerInfoService customerInfoService;
	@Autowired
	private CountryService countryService;
	@Autowired
	private AuthService authService;

	@Value("${appid}")
	private String appId;
	@Value("${secret}")
	private String secret;

	@Test
	public void payTest() {
		WXPay wxPay = WpayUtil.getWXPay("www.tom235.com");
		Map<String, String> dataMap = new HashMap<>();
		String outTradeNo = System.currentTimeMillis()+"";
		dataMap.put("openid","oqrTq4jLQt0I_9F4vQVQLQGDrBbM");
		dataMap.put("out_trade_no",outTradeNo);
		dataMap.put("body","支付测试数据");
		dataMap.put("total_fee","1");
		dataMap.put("notify_url","www.tom235.com");
		dataMap.put("trade_type","JSAPI");


//		dataMap.put("signType","MD5");
		try {
			dataMap = wxPay.fillRequestData(dataMap);
			Map<String, String> result = wxPay.unifiedOrder(dataMap);
			String prepayId = result.get("prepay_id");
			log.info("prepayId:{}",prepayId);
			log.info("result:{}",result);
			String packageStr ="prepay_id="+prepayId;
			Map<String,String> payMap = new HashMap<>();
//			payMap.put("package",packageStr);
//			payMap.put("total_fee","1");
			payMap.put("appid","wx92ead9e82f3d55e5");
			payMap.put("time_stamp",(System.currentTimeMillis()/1000)+"");
//			payMap.put("paySign","111");
			payMap.put("package",packageStr);
			result = wxPay.microPayWithPos(payMap);

			log.info("result:{}",result);
		} catch(Exception ex) {
			ex.printStackTrace();
		}



	}

	@Test
	public void test2() {
		CountryExample countryExample = new CountryExample();
		countryExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code());
		List<CountryDto> countryDtoList = countryService.getList(countryExample);

		log.info("countryDtoList:{}", countryDtoList);
	}

//	@Test
//	void test1() {
//		LsCodeGeneratorUtil.generateCode("app", "com.caichao.chateau", "jdbc:mysql://www"
//				+ ".tom235.com:3306/chateau?useUnicode=true&characterEncoding=utf8", "com.mysql.cj.jdbc.Driver",
//			"chisong", "csz123$%", Arrays.asList(GenerateFileTypeEnum.DTO,
//				GenerateFileTypeEnum.EXAMPLE, GenerateFileTypeEnum.ENTITY),
//			"order_info");
////		LsCodeGeneratorUtil.generateCode("app", "com.caichao.chateau", "jdbc:mysql://www"
////				+ ".tom235.com:3306/chateau?useUnicode=true&characterEncoding=utf8", "com.mysql.cj.jdbc.Driver",
////			"chisong", "csz123$%", "cart_item");
//	}

	@Test
	public void test3() {
		LoginReq loginReq = new LoginReq();
		loginReq.setJsCode("111111111");
		loginReq.setAppId(appId);
		loginReq.setSecret(secret);
		LoginResponse loginResponse = authService.codeToSession(loginReq);
		log.info("loginResponse=>{}", loginResponse);
	}

	@Test
	public void test4() {
		AcccessCodeReq acccessCodeReq = new AcccessCodeReq();
		acccessCodeReq.setAppId(appId);
		acccessCodeReq.setSecret(secret);

		AccessCodeResponse accessCodeResponse = authService.getAccessToken(acccessCodeReq);
		log.info("accessCodeResponse:{}", accessCodeResponse);


	}
}
