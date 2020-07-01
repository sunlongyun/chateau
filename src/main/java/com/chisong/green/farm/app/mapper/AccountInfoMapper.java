package com.chisong.green.farm.app.mapper;

import com.chisong.green.farm.app.entity.AccountInfo;
import com.lianshang.generator.commons.LsBaseMapper;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 供应商账户信息 Mapper 接口
 * </p>
 *
 * @author 孙龙云
 * @since 2020-05-04
 */
public interface AccountInfoMapper extends LsBaseMapper<AccountInfo> {

	/**
	 * 统计天周月业绩汇总
	 * @param customerId
	 * @return
	 */
	Map<String,Object> getDayWeekMonthSummaryInfo(Long customerId);

	/**
	 * 冻结
	 * @param amount
	 * @param accountId
	 * @return
	 */
	int frozenAmount(@Param("amount") Integer amount, @Param("accountId") Integer accountId);
}
