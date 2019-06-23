package com.caichao.chateau.app.controller.chateau;

import com.caichao.chateau.app.constants.enums.Validity;
import com.caichao.chateau.app.controller.response.CCResponse;
import com.caichao.chateau.app.dto.CountryChateauDto;
import com.caichao.chateau.app.example.CountryChateauExample;
import com.caichao.chateau.app.service.CountryChateauService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 * 庄园管理controller
 *
 * @AUTHOR 孙龙云
 * @date 2019-06-17 21:55
 */
@RestController
@RequestMapping("chateau")
public class ChateauController {

	@Autowired
	private CountryChateauService countryChateauService;

	/**
	 * 根据国家查询酒庄列表
	 * @param countryId
	 * @return
	 */
	@RequestMapping("getList")
	public CCResponse getList(Integer countryId) {
		CountryChateauExample countryChateauExample = new CountryChateauExample();
		countryChateauExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code()).andCountryIdEqualTo(countryId);

		List<CountryChateauDto> chateauDtoList = countryChateauService.getList(countryChateauExample);
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("list", chateauDtoList);
		return CCResponse.success(dataMap);
	}
}
