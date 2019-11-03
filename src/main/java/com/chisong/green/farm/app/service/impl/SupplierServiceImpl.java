package com.chisong.green.farm.app.service.impl;

import com.chisong.green.farm.app.entity.Supplier;
import com.chisong.green.farm.app.dto.SupplierDto;
import com.chisong.green.farm.app.mapper.SupplierMapper;
import com.chisong.green.farm.app.service.SupplierService;

import com.lianshang.generator.commons.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 供应商 服务实现类
 * </p>
 *
 * @author 孙龙云
 * @since 2019-07-02
 */
@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier, SupplierDto> implements SupplierService {

}