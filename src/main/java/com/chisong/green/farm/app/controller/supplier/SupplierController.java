package com.chisong.green.farm.app.controller.supplier;

import com.chisong.green.farm.app.constants.enums.Validity;
import com.chisong.green.farm.app.controller.response.CCResponse;
import com.chisong.green.farm.app.dto.SupplierDto;
import com.chisong.green.farm.app.example.SupplierExample;
import com.chisong.green.farm.app.example.SupplierExample.Criteria;
import com.chisong.green.farm.app.service.SupplierService;
import com.lianshang.generator.commons.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 * 供应商管理controller
 *
 * @AUTHOR 孙龙云
 * @date 2019-07-02 22:03
 */
@RequestMapping("supplier")
@RestController
public class SupplierController {

	@Autowired
	private SupplierService supplierService;

	/**
	 * 查询供应商列表
	 */
	@RequestMapping("/getPageInfo")
	public CCResponse getPageInfo(Integer pageNo, Integer pageSize, String supplierName, Integer chateauId) {
		SupplierExample supplierExample = new SupplierExample();
		Criteria criteria = supplierExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code());

		if(!StringUtils.isEmpty(supplierName)){
			criteria.andCompanyNameLike("%"+supplierName+"%");
		}
		PageInfo<SupplierDto> supplierDtoPageInfo = supplierService.getPageInfo(pageNo,pageSize,supplierExample);

		return CCResponse.success(supplierDtoPageInfo);
	}
}
