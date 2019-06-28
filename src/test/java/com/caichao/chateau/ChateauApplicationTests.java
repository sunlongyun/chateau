package com.caichao.chateau;

import com.caichao.chateau.app.constants.enums.Validity;
import com.caichao.chateau.app.dto.CountryDto;
import com.caichao.chateau.app.example.CountryExample;
import com.caichao.chateau.app.miniProgram.request.AcccessCodeReq;
import com.caichao.chateau.app.miniProgram.request.LoginReq;
import com.caichao.chateau.app.miniProgram.request.PrePayRequest;
import com.caichao.chateau.app.miniProgram.response.AccessCodeResponse;
import com.caichao.chateau.app.miniProgram.response.LoginResponse;
import com.caichao.chateau.app.miniProgram.response.PrePayResponse;
import com.caichao.chateau.app.miniProgram.service.AuthService;
import com.caichao.chateau.app.miniProgram.service.WxPayService;
import com.caichao.chateau.app.service.CountryService;
import com.caichao.chateau.app.service.CustomerInfoService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private WxPayService wxPayService;

	@Test
	public void payTest() {
		PrePayRequest prePayRequest = new PrePayRequest();
		prePayRequest.setBody("测试商品");
		prePayRequest.setTradeType("JSAPI");
		String outTradeNo = System.currentTimeMillis() + "";
		prePayRequest.setOutTradeNo(outTradeNo);
		prePayRequest.setTotalFee("1");
		prePayRequest.setOpenid("oqrTq4jLQt0I_9F4vQVQLQGDrBbM");
		prePayRequest.setSpbillCreateIp("127.0.0.1");
		prePayRequest.setNotifyUrl("www.tom235.com");
		PrePayResponse prePayResponse = wxPayService.prePay(prePayRequest);
		log.info("返回的预支付流水:{}",prePayResponse);
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


}
