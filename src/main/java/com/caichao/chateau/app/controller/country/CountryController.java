package com.caichao.chateau.app.controller.country;

import com.caichao.chateau.app.constants.enums.Validity;
import com.caichao.chateau.app.controller.country.response.CountryInfoDto;
import com.caichao.chateau.app.controller.response.CCResponse;
import com.caichao.chateau.app.dto.CountryChateauDto;
import com.caichao.chateau.app.example.CountryChateauExample;
import com.caichao.chateau.app.example.CountryExample;
import com.caichao.chateau.app.service.CountryChateauService;
import com.caichao.chateau.app.service.CountryService;
import com.lianshang.generator.commons.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 *
 * @AUTHOR 孙龙云
 * @date 2019-06-17 21:39
 */
@RequestMapping("/country")
@RestController
public class CountryController {

	@Autowired
	private CountryService countryService;
	@Autowired
	private CountryChateauService countryChateauService;

	/**
	 * 获取国家庄园列表
	 */
	@RequestMapping("chateau/getList")
	public CCResponse getCountryChateauList() {
		CountryExample countryExample = new CountryExample();
		countryExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code());
		countryService.getList(countryExample).stream().map(countryDto -> {
			CountryInfoDto countryInfoDto = new CountryInfoDto();
			BeanUtils.copyProperties(countryDto, countryInfoDto);

			CountryChateauExample countryChateauExample = new CountryChateauExample();
			countryChateauExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code()).andCountryIdEqualTo
				(countryDto.getId());

			PageInfo<CountryChateauDto> pageInfo = countryChateauService.getPageInfo(1,4,countryChateauExample);
			countryInfoDto.setChateauNum(pageInfo.getTotal());
			countryInfoDto.setChateauList(pageInfo.getDataList());
			return countryInfoDto;
		});
		return CCResponse.success();
	}

}
