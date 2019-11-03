package com.chisong.green.farm.app.controller.images;

import com.chisong.green.farm.app.constants.enums.PictureTypeEnum;
import com.chisong.green.farm.app.constants.enums.Validity;
import com.chisong.green.farm.app.controller.response.CCResponse;
import com.chisong.green.farm.app.dto.PicturesDto;
import com.chisong.green.farm.app.example.PicturesExample;
import com.chisong.green.farm.app.service.PicturesService;
import com.chisong.green.farm.app.utils.IPUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 *
 * @AUTHOR 孙龙云
 * @date 2019-06-17 21:13
 */
@RequestMapping("images")
@RestController
@Slf4j
public class ImagesController {

	@Autowired
	private PicturesService picturesService;

	/**
	 * 获取首页轮播图片
	 */
	@RequestMapping("getRotaryPictures")
	public CCResponse getRotaryPictures() {
		PicturesExample picturesExample = new PicturesExample();
		picturesExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code()).andTypeEqualTo(
			PictureTypeEnum.FIRST_PAGE_ROLL.code());

		List<PicturesDto> picturesDtoList = picturesService.getList(picturesExample);
		Map<Object, Object> dataMap = new HashMap<>();
		dataMap.put("pictures", picturesDtoList);

		return CCResponse.success(dataMap);
	}

	/**
	 * 获取国家葡萄酒引导图片
	 */
	@RequestMapping("getCountry-navigation")
	public CCResponse getCountryNavigation() {
		String ip = IPUtil.getIpAddr();
		log.info("ip:{}", ip);
		PicturesExample picturesExample = new PicturesExample();
		picturesExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code()).andTypeEqualTo(
			PictureTypeEnum.COUNTRY_NAVIGATION.code());

		List<PicturesDto> picturesDtoList = picturesService.getList(picturesExample);
		Map<Object, Object> dataMap = new HashMap<>();
		if(!CollectionUtils.isEmpty(picturesDtoList)) {
			dataMap.put("img", picturesDtoList.get(0));
		}
		return CCResponse.success(dataMap);
	}
}
