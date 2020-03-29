package com.chisong.green.farm.app.controller.goodsType;

import com.chisong.green.farm.app.constants.enums.Validity;
import com.chisong.green.farm.app.controller.response.CCResponse;
import com.chisong.green.farm.app.example.GoodsTypeExample;
import com.chisong.green.farm.app.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 *
 * @AUTHOR 孙龙云
 * @date 2020-03-28 16:53
 */
@RestController
@RequestMapping("/goodsType")
public class GoodsTypeController {
	@Autowired
	private GoodsTypeService goodsTypeService;

	@RequestMapping("/list/{parentId}")
	public CCResponse getList(@PathVariable Integer parentId){
		GoodsTypeExample goodsTypeExample = new GoodsTypeExample();
		goodsTypeExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code())
			.andParentIdEqualTo(parentId);
		goodsTypeExample.setOrderByClause("weight desc");
		return CCResponse.success(goodsTypeService.getList(goodsTypeExample));
	}
}
