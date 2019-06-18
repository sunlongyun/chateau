package com.caichao.chateau.app.controller.beverage;

import com.caichao.chateau.app.controller.beverage.request.PageQueryReq;
import com.caichao.chateau.app.controller.response.CCResponse;
import com.caichao.chateau.app.dto.CountryChateauBeverageDto;
import com.caichao.chateau.app.service.CountryChateauBeverageService;
import com.lianshang.generator.commons.PageInfo;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 * 酒水管理controller
 *
 * @AUTHOR 孙龙云
 * @date 2019-06-18 7:17
 */
@RestController
public class BeverageController {

	@Autowired
	private CountryChateauBeverageService countryChateauBeverageService;

	/**
	 * 分页查询
	 * @param pageQueryReq
	 * @return
	 */
	@RequestMapping("getList")
	public CCResponse getList(@RequestBody PageQueryReq pageQueryReq) {
		PageInfo pageInfo = countryChateauBeverageService.getCountryChateauBeverageDtoPageInfo(pageQueryReq);
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("pageInfo", pageInfo);
		return CCResponse.success(dataMap);
	}

	/**
	 * 查询酒水详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/getDetail")
	public CCResponse getDetail(Long id){
		CountryChateauBeverageDto countryChateauBeverageDto = countryChateauBeverageService.getById(id);
		return CCResponse.success(countryChateauBeverageDto);
	}
}
