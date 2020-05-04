package com.chisong.green.farm.app.service.impl;

import com.chisong.green.farm.app.entity.AccountFlow;
import com.chisong.green.farm.app.dto.AccountFlowDto;
import com.chisong.green.farm.app.mapper.AccountFlowMapper;
import com.chisong.green.farm.app.service.AccountFlowService;

import com.lianshang.generator.commons.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账户流水 服务实现类
 * </p>
 *
 * @author 孙龙云
 * @since 2020-05-04
 */
@Service
public class AccountFlowServiceImpl extends ServiceImpl<AccountFlowMapper,AccountFlow, AccountFlowDto> implements AccountFlowService {

}