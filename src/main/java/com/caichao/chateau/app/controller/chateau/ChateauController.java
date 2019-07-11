package com.caichao.chateau.app.controller.chateau;

import com.caichao.chateau.app.constants.enums.BroadCastStatusEnum;
import com.caichao.chateau.app.constants.enums.BroadCastTypeEnum;
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
	 * 修改直播状态
	 */
	@RequestMapping("updateBroadCastStatus")
	public CCResponse updateBroadCastStatus(Integer chateauId, Integer type, Integer status) {
		CountryChateauDto countryChateauDto = countryChateauService.getById(chateauId);
		if(null == countryChateauDto) {
			throw new RuntimeException("酒庄不存在");
		}
		BroadCastTypeEnum broadCastTypeEnum = BroadCastTypeEnum.getBroadCastTypeEnum(type);
		if(null == broadCastTypeEnum) {
			throw new RuntimeException("直播类型错误");
		}

		BroadCastStatusEnum broadCastStatusEnum = BroadCastStatusEnum.getBroadCastStatusEnum(status);
		if(null == broadCastStatusEnum) {
			throw new RuntimeException("直播状态错误");
		}

		if(broadCastTypeEnum == BroadCastTypeEnum.DAILY) {
			if(broadCastStatusEnum == BroadCastStatusEnum.ING) {
				countryChateauDto.setDailyBroadcastIng(1);
			} else if(broadCastStatusEnum == BroadCastStatusEnum.UN) {
				countryChateauDto.setDailyBroadcastIng(0);
			}
		} else if(broadCastTypeEnum == BroadCastTypeEnum.MASTER) {
			if(broadCastStatusEnum == BroadCastStatusEnum.ING) {
				countryChateauDto.setMasterBroadcastIng(1);
			} else if(broadCastStatusEnum == BroadCastStatusEnum.UN) {
				countryChateauDto.setMasterBroadcastIng(0);
			}
		}

		countryChateauService.update(countryChateauDto);
		return CCResponse.success();
	}


	/**
	 * 根据国家查询酒庄列表
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

	/**
	 * 获取庄园详情
	 */
	@RequestMapping("/getDetail")
	public CCResponse getDetail(Integer id) {
		CountryChateauDto countryChateauDto = countryChateauService.getById(id);
		return CCResponse.success(countryChateauDto);
	}

	/**
	 * 获取推送地址
	 */
	@RequestMapping("/getPusherUrl")
	public CCResponse getPusherUrl(Integer id, Integer type) {
		Map<String, String> data = new HashMap<>();
		String url = "";
		if(type == 1) {
			url = countryChateauService.getDailyPusherUlr(id);
		} else {
			url = countryChateauService.getMasterPusherUlr(id);
		}
		data.put("pusherUrl", url);

		return CCResponse.success(data);
	}
}
