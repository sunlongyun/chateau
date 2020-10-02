package com.chisong.green.farm.app.service.impl;

import com.chisong.green.farm.app.entity.AppInfo;
import com.chisong.green.farm.app.dto.AppInfoDto;
import com.chisong.green.farm.app.mapper.AppInfoMapper;
import com.chisong.green.farm.app.service.AppInfoService;

import com.lianshang.generator.commons.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * APP名称 服务实现类
 * </p>
 *
 * @author 孙龙云
 * @since 2020-10-02
 */
@Service
public class AppInfoServiceImpl extends ServiceImpl<AppInfoMapper,AppInfo, AppInfoDto> implements AppInfoService {

}