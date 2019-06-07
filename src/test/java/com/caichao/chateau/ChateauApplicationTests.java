package com.caichao.chateau;

import com.caichao.chateau.app.dto.CustomerInfoDto;
import com.caichao.chateau.app.service.CustomerInfoService;
import com.lianshang.utils.LsCodeGeneratorUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ChateauApplicationTests {
	@Autowired
	private CustomerInfoService customerInfoService;
	@Test
	public void test2(){
		CustomerInfoDto customerInfoDto = new CustomerInfoDto();
		customerInfoDto.setMobile("13681967316");
		customerInfoDto.setOpenId("openId");
		customerInfoDto.setPassWord("hello");
		customerInfoDto.setUserName("孙龙云");
		customerInfoService.save(customerInfoDto);
	}

	@Test
	void test1() {
		LsCodeGeneratorUtil.generateCode("app","com.caichao.chateau","jdbc:mysql://www"
			+ ".tom235.com:3306/chateau?useUnicode=true&characterEncoding=utf8","com.mysql.cj.jdbc.Driver",
			"chisong","csz123$%",
			"customer_info");
	}

}
