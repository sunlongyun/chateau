package com.chisong.green.farm.app.service.impl;

import com.chisong.green.farm.app.entity.Games;
import com.chisong.green.farm.app.dto.GamesDto;
import com.chisong.green.farm.app.mapper.GamesMapper;
import com.chisong.green.farm.app.service.GamesService;

import com.lianshang.generator.commons.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 游戏服务推广表 服务实现类
 * </p>
 *
 * @author 孙龙云
 * @since 2019-12-01
 */
@Service
public class GamesServiceImpl extends ServiceImpl<GamesMapper,Games, GamesDto> implements GamesService {

}