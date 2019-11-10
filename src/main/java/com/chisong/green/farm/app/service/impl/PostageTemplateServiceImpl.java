package com.chisong.green.farm.app.service.impl;

import com.chisong.green.farm.app.entity.PostageTemplate;
import com.chisong.green.farm.app.dto.PostageTemplateDto;
import com.chisong.green.farm.app.mapper.PostageTemplateMapper;
import com.chisong.green.farm.app.service.PostageTemplateService;

import com.lianshang.generator.commons.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 运费模板 服务实现类
 * </p>
 *
 * @author 孙龙云
 * @since 2019-11-10
 */
@Service
public class PostageTemplateServiceImpl extends ServiceImpl<PostageTemplateMapper,PostageTemplate, PostageTemplateDto> implements PostageTemplateService {

}