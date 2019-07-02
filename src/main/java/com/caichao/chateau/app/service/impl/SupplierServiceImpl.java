package com.caichao.chateau.app.service.impl;

import com.caichao.chateau.app.entity.Supplier;
import com.caichao.chateau.app.dto.SupplierDto;
import com.caichao.chateau.app.mapper.SupplierMapper;
import com.caichao.chateau.app.service.SupplierService;

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
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper,Supplier, SupplierDto> implements SupplierService {

}