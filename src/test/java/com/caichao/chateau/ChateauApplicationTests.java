package com.caichao.chateau;

import com.caichao.chateau.app.dto.CustomerInfoDto;
import com.caichao.chateau.app.service.CustomerInfoService;
import com.lianshang.generator.commons.PageInfo;
import com.lianshang.utils.LsCodeGeneratorUtil;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ChateauApplicationTests {

	@Autowired
	private CustomerInfoService customerInfoService;
	@Test
	public void testCustomerInfoService(){
	PageInfo<CustomerInfoDto> customerInfoDtoPageInfo = customerInfoService.getPageInfo(1,10,null);
	log.info("customerInfoDtoPageInfo:{}", customerInfoDtoPageInfo);
	}


	@Test
	public void test4() {
		LsCodeGeneratorUtil.generateCode("app", "com.caichao.chateau", "jdbc:mysql://www"
				+ ".tom235.com:3306/green_farm?useUnicode=true&characterEncoding=utf8", "com.mysql.jdbc.Driver",
			"chisong", "csz123$%",
			"goods");
	}


}
