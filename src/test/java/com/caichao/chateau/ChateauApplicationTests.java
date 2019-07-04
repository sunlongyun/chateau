package com.caichao.chateau;

import com.caichao.chateau.app.constants.enums.Validity;
import com.caichao.chateau.app.dto.CountryDto;
import com.caichao.chateau.app.example.CountryExample;
import com.caichao.chateau.app.miniProgram.request.PayOrderQuery;
import com.caichao.chateau.app.miniProgram.request.PrePayRequest;
import com.caichao.chateau.app.miniProgram.response.PayOrderQueryResultResponse;
import com.caichao.chateau.app.miniProgram.response.PrePayResponse;
import com.caichao.chateau.app.miniProgram.service.AuthService;
import com.caichao.chateau.app.miniProgram.service.WxPayService;
import com.caichao.chateau.app.service.CountryService;
import com.caichao.chateau.app.service.CustomerInfoService;
import com.caichao.chateau.app.wpay.util.WpayUtil;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
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

	@Autowired
	private WxPayService wxPayService;
	@Value("${mchID}")
	private String merchId;
	@Value("${appid}")
	private String appId;
	@Value("${key}")
	private String key;

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
		log.info("返回的预支付流水:{}", prePayResponse);

		String packageStr="prePayId="+prePayResponse.getPrepayId();
		Map<String,String> payMap = new HashMap<>();
			payMap.put("package",packageStr);
		payMap.put("appid","wx92ead9e82f3d55e5");
		payMap.put("time_stamp",(System.currentTimeMillis()/1000)+"");
		payMap.put("package",packageStr);
		payMap.put("total_fee","1000");
		WXPay wxPay = WpayUtil.getWXPay(merchId,appId,key,null,null);
		try {

			Map<String, String> result = wxPay.microPayWithPos(payMap);
			log.info("result:{}", result);
		}catch(Exception ex){
			ex.printStackTrace();
		}

		PayOrderQuery payOrderQuery = new PayOrderQuery();
		payOrderQuery.setOutTradeNo(outTradeNo);

		PayOrderQueryResultResponse payOrderQueryResultResponse = wxPayService.queryPayOrder(payOrderQuery);
		log.info("payOrderQueryResultResponse:{}", payOrderQueryResultResponse);
	}

	@Test
	public void test2() {
		CountryExample countryExample = new CountryExample();
		countryExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code());
		List<CountryDto> countryDtoList = countryService.getList(countryExample);

		log.info("countryDtoList:{}", countryDtoList);
	}

	@Test
	void test1() {
		LsCodeGeneratorUtil.generateCode("app", "com.caichao.chateau", "jdbc:mysql://www"
				+ ".tom235.com:3306/chateau?useUnicode=true&characterEncoding=utf8", "com.mysql.cj.jdbc.Driver",
			"chisong", "csz123$%", Arrays.asList(GenerateFileTypeEnum.DTO,GenerateFileTypeEnum.MAPPER_XML,
				GenerateFileTypeEnum.EXAMPLE, GenerateFileTypeEnum.ENTITY),
			"supplier");
//		LsCodeGeneratorUtil.generateCode("app", "com.caichao.chateau", "jdbc:mysql://www"
//				+ ".tom235.com:3306/chateau?useUnicode=true&characterEncoding=utf8", "com.mysql.cj.jdbc.Driver",
//			"chisong", "csz123$%", "cart_item");
	}
	@Test
public void test3(){
		log.info((System.currentTimeMillis()/1000)+"");
}

}
