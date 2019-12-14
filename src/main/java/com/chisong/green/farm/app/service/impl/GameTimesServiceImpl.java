package com.chisong.green.farm.app.service.impl;

import com.chisong.green.farm.app.entity.GameTimes;
import com.chisong.green.farm.app.dto.GameTimesDto;
import com.chisong.green.farm.app.mapper.GameTimesMapper;
import com.chisong.green.farm.app.service.GameTimesService;

import com.lianshang.generator.commons.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 孙龙云
 * @since 2019-12-07
 */
@Service
public class GameTimesServiceImpl extends ServiceImpl<GameTimesMapper,GameTimes, GameTimesDto> implements GameTimesService {

}