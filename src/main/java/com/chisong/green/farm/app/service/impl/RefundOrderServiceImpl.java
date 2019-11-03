package com.chisong.green.farm.app.service.impl;

import com.chisong.green.farm.app.constants.enums.Validity;
import com.chisong.green.farm.app.entity.RefundOrder;
import com.chisong.green.farm.app.dto.RefundOrderDto;
import com.chisong.green.farm.app.example.RefundOrderExample;
import com.chisong.green.farm.app.mapper.RefundOrderMapper;
import com.chisong.green.farm.app.service.RefundOrderService;

import com.lianshang.generator.commons.ServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * <p>
 * 退款申请单 服务实现类
 * </p>
 *
 * @author 孙龙云
 * @since 2019-07-28
 */
@Service
public class RefundOrderServiceImpl extends ServiceImpl<RefundOrderMapper, RefundOrder, RefundOrderDto> implements
	RefundOrderService {

	@Override
	public RefundOrderDto getRefundOrderDto(String applyNo) {
		RefundOrderExample refundOrderExample = new RefundOrderExample();
		refundOrderExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code())
			.andRefundNoEqualTo(applyNo);

		List<RefundOrderDto> refundOrderDtoList =	getList(refundOrderExample);
		if(!CollectionUtils.isEmpty(refundOrderDtoList)){
			return refundOrderDtoList.get(0);
		}
		return null;
	}
}