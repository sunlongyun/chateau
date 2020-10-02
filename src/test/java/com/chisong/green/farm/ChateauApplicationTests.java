package com.chisong.green.farm;

import com.chisong.green.farm.app.dto.CustomerInfoDto;
import com.chisong.green.farm.app.example.ProvinceCityAreaExample;
import com.chisong.green.farm.app.miniProgram.msg.TextMsg;
import com.chisong.green.farm.app.miniProgram.request.PayToPersonRequest;
import com.chisong.green.farm.app.miniProgram.response.PayToPersonResponse;
import com.chisong.green.farm.app.miniProgram.service.WxPayService;
import com.chisong.green.farm.app.service.AuthBizService;
import com.chisong.green.farm.app.service.CustomerCenterService;
import com.chisong.green.farm.app.service.CustomerInfoService;
import com.chisong.green.farm.app.service.ProvinceCityAreaService;
import com.lianshang.generator.commons.GenerateFileTypeEnum;
import com.lianshang.generator.commons.PageInfo;
import com.lianshang.utils.LsCodeGeneratorUtil;

import java.lang.reflect.Array;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import org.springframework.transaction.config.TxNamespaceHandler;

@SpringBootTest(classes =ChateauApplication.class )
@Slf4j
public class ChateauApplicationTests {

	@Autowired
	private CustomerInfoService customerInfoService;

	@Autowired
	private ProvinceCityAreaService provinceCityAreaService;

	@Autowired
	private CustomerCenterService customerCenterService;
	@Autowired
	private AuthBizService authBizService;
	@Autowired
	private WxPayService wxPayService;


	@Test
	public void testPay(){
		PayToPersonRequest payToPersonRequest = new PayToPersonRequest();
		payToPersonRequest.setAmount(100);
		payToPersonRequest.setCheckName("FORCE_CHECK");
		payToPersonRequest.setReUserName("孙龙云");
		payToPersonRequest.setPartnerTradeNo("111111");
		payToPersonRequest.setDesc("付款测试");
		payToPersonRequest.setOpenid("oqrTq4jLQt0I_9F4vQVQLQGDrBbM");

		PayToPersonResponse payToPersonResponse = wxPayService.payToPerson(payToPersonRequest);
	}

	@Test
	public void testArea(){
		ProvinceCityAreaExample provinceCityAreaExample = new ProvinceCityAreaExample();
		int count =  provinceCityAreaService.getCount(provinceCityAreaExample);
		log.info("count:{}", count);
	}
	@Test
	public void testCustomerInfoService(){
	PageInfo<CustomerInfoDto> customerInfoDtoPageInfo = customerInfoService.getPageInfo(1,10,null);
	log.info("customerInfoDtoPageInfo:{}", customerInfoDtoPageInfo);
	}


	@Test
	public void test4() {
		LsCodeGeneratorUtil.generateCode("app", "com.chisong.green.farm", "jdbc:mysql://www"
				+ ".tom235.com:3306/green_farm?useUnicode=true&characterEncoding=utf8", "com.mysql.jdbc.Driver",
			"chisong", "csz123$%",
			"app_info");
	/*	LsCodeGeneratorUtil.generateCode("app", "com.chisong.green.farm", "jdbc:mysql://www"
				+ ".tom235.com:3306/green_farm?useUnicode=true&characterEncoding=utf8", "com.mysql.jdbc.Driver",
			"chisong", "csz123$%",Arrays.asList(GenerateFileTypeEnum.DTO,GenerateFileTypeEnum.EXAMPLE,
				GenerateFileTypeEnum.ENTITY,GenerateFileTypeEnum.EXAMPLE),
			"with_draw_apply");*/
	}

	@Test
	public void sendMsg(){
		TextMsg textMsg = new TextMsg();
		TextMsg.Content content = 	new TextMsg.Content();
		content.setContent("你好,很高兴认识你");
//		textMsg.setTouser("oqrTq4oTt7NVNNNeGSr2XafYqd-o");
		textMsg.setAccessToken(authBizService.getAccessToken());
		customerCenterService.sendToCustomer(textMsg);
	}

	@Test
	public void genereate(){
		ProvinceCityAreaExample provinceCityAreaExample = new ProvinceCityAreaExample();
		 provinceCityAreaService.getList(provinceCityAreaExample).stream().forEach(provinceCityAreaDto -> {
			 provinceCityAreaDto.setAppInfoId(2L);
			 provinceCityAreaService.save(provinceCityAreaDto);
		 });
	}

}
