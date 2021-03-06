package com.chisong.green.farm.app.controller.goods;

import com.chisong.green.farm.app.constants.enums.GoodsStatusEnum;
import com.chisong.green.farm.app.controller.goods.request.PageQueryReq;
import com.chisong.green.farm.app.controller.response.CCResponse;
import com.chisong.green.farm.app.dto.GoodsDto;
import com.chisong.green.farm.app.service.GoodsService;
import com.lianshang.generator.commons.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 * 酒水管理controller
 *
 * @AUTHOR 孙龙云
 * @date 2019-06-18 7:17
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {


	@Autowired
	private GoodsService goodsService;
	/**
	 * 分页查询
	 * @param pageQueryReq
	 * @return
	 */
	@RequestMapping("getList")
	public CCResponse getList(PageQueryReq pageQueryReq) {
		pageQueryReq.setStatus(GoodsStatusEnum.NORMAL.code());
		PageInfo pageInfo = goodsService.getGoodsInfo(pageQueryReq);
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
		GoodsDto goodsDto = goodsService.getDetailById(id);

		return CCResponse.success(goodsDto);
	}
}
