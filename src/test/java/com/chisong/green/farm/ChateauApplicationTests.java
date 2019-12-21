package com.chisong.green.farm;

import com.chisong.green.farm.app.dto.CustomerInfoDto;
import com.chisong.green.farm.app.example.ProvinceCityAreaExample;
import com.chisong.green.farm.app.service.CustomerInfoService;
import com.chisong.green.farm.app.service.ProvinceCityAreaService;
import com.lianshang.generator.commons.GenerateFileTypeEnum;
import com.lianshang.generator.commons.PageInfo;
import com.lianshang.utils.LsCodeGeneratorUtil;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
@Slf4j
public class ChateauApplicationTests {

	@Autowired
	private CustomerInfoService customerInfoService;

	@Autowired
	private ProvinceCityAreaService provinceCityAreaService;

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
				GenerateFileTypeEnum.EXAMPLE),
			"order_detail");
	}


}
