package com.chisong.green.farm;

import com.chisong.green.farm.app.dto.CustomerInfoDto;
import com.chisong.green.farm.app.example.ProvinceCityAreaExample;
import com.chisong.green.farm.app.miniProgram.msg.TextMsg;
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

@SpringBootTest
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
//		LsCodeGeneratorUtil.generateCode("app", "com.chisong.green.farm", "jdbc:mysql://www"
//				+ ".tom235.com:3306/green_farm?useUnicode=true&characterEncoding=utf8", "com.mysql.jdbc.Driver",
//			"chisong", "csz123$%",
//			"game_times");
		LsCodeGeneratorUtil.generateCode("app", "com.chisong.green.farm", "jdbc:mysql://www"
				+ ".tom235.com:3306/green_farm?useUnicode=true&characterEncoding=utf8", "com.mysql.jdbc.Driver",
			"chisong", "csz123$%",Arrays.asList(GenerateFileTypeEnum.ENTITY,GenerateFileTypeEnum.DTO,
				GenerateFileTypeEnum.EXAMPLE, GenerateFileTypeEnum.MAPPER_XML),
			"summary_info");
	}

	@Test
	public void sendMsg(){
		TextMsg textMsg = new TextMsg();
		TextMsg.Content content = 	new TextMsg.Content();
		content.setContent("你好,很高兴认识你");
		textMsg.setText(content);
		textMsg.setTouser("oqrTq4jLQt0I_9F4vQVQLQGDrBbM");
		textMsg.setAccessToken(authBizService.getAccessToken());
		customerCenterService.sendToCustomer(textMsg);
	}

}
