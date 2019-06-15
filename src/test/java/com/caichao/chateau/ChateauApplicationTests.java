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
import java.util.List;
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
//			"chisong", "csz123$%", "cart_beverage_mapping",
//			"country",
//			"country_chateau",
//			"country_chateau_beverage",
//			"customer_delivery_address",
//			"customer_info",
//			"order_delivery_address_mapping",
//			"order_detail",
//			"order_info",
//			"payment",
//			"pictures",
//			"shopping_cart");
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
