package com.caichao.chateau.app.controller.supplier;

import com.caichao.chateau.app.constants.enums.Validity;
import com.caichao.chateau.app.controller.response.CCResponse;
import com.caichao.chateau.app.dto.SupplierChateauMappingDto;
import com.caichao.chateau.app.dto.SupplierDto;
import com.caichao.chateau.app.example.SupplierChateauMappingExample;
import com.caichao.chateau.app.example.SupplierExample;
import com.caichao.chateau.app.example.SupplierExample.Criteria;
import com.caichao.chateau.app.service.SupplierChateauMappingService;
import com.caichao.chateau.app.service.SupplierService;
import com.lianshang.generator.commons.PageInfo;
import java.util.List;
import java.util.stream.Collectors;
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
	private SupplierChateauMappingService supplierChateauMappingService;
	@Autowired
	private SupplierService supplierService;

	/**
	 * 查询供应商列表
	 */
	@RequestMapping("/getPageInfo")
	public CCResponse getPageInfo(Integer pageNo, Integer pageSize, String supplierName, Integer chateauId) {
		SupplierExample supplierExample = new SupplierExample();
		Criteria criteria = supplierExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code());
		if(null != chateauId) {
			SupplierChateauMappingExample supplierChateauMappingExample = new SupplierChateauMappingExample();
			supplierChateauMappingExample.createCriteria().andValidityEqualTo(chateauId);
			List<Integer> supplierIdList = supplierChateauMappingService.getList(supplierChateauMappingExample).stream()
				.map
					(SupplierChateauMappingDto::getSupplierId).collect(Collectors.toList());
			criteria.andIdIn(supplierIdList);
		}
		if(!StringUtils.isEmpty(supplierName)){
			criteria.andCompanyNameLike("%"+supplierName+"%");
		}
		PageInfo<SupplierDto> supplierDtoPageInfo = supplierService.getPageInfo(pageNo,pageSize,supplierExample);

		return CCResponse.success(supplierDtoPageInfo);
	}
}
