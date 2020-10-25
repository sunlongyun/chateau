package com.chisong.green.farm.app.service.impl;

import com.chisong.green.farm.app.constants.enums.Validity;
import com.chisong.green.farm.app.entity.PostageTemplate;
import com.chisong.green.farm.app.dto.PostageTemplateDto;
import com.chisong.green.farm.app.example.PostageTemplateExample;
import com.chisong.green.farm.app.mapper.PostageTemplateMapper;
import com.chisong.green.farm.app.service.PostageTemplateService;

import com.chisong.green.farm.app.utils.AppUtils;
import com.lianshang.generator.commons.ServiceImpl;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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

	@Override
	@Transactional
	public void saveDefaultTemplate(Long goodsId) {
		PostageTemplateExample postageTemplateExample = new PostageTemplateExample();
		postageTemplateExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code())
		.andGoodsIdEqualTo(goodsId);
		List<PostageTemplate> postageTemplateDtoList = this.baseMapper.selectByExample(postageTemplateExample);

		if(!CollectionUtils.isEmpty(postageTemplateDtoList)){
			return;
		}
		 postageTemplateExample = new PostageTemplateExample();
		postageTemplateExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code())
			.andGoodsIdEqualTo(0l);
		 postageTemplateDtoList = this.baseMapper.selectByExample(postageTemplateExample);
		postageTemplateDtoList.stream().forEach(postageTemplate -> {
			postageTemplate.setGoodsId(goodsId);
			this.baseMapper.insert(postageTemplate);
		});
	}

	@Override
	public void savePostageTemplateDtoList(List<PostageTemplateDto> postageTemplateDtos) {
		if(CollectionUtils.isEmpty(postageTemplateDtos)){
			throw new RuntimeException("模板列表不能为空");
		}
		Long goodsId = postageTemplateDtos.get(0).getGoodsId();
		if(null == goodsId){
			throw new RuntimeException("商品id不能为空");
		}

		PostageTemplateExample postageTemplateExample = new PostageTemplateExample();
		postageTemplateExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code())
			.andGoodsIdEqualTo(goodsId);

		List<PostageTemplateDto> postageTemplateDtoList = this.getList(postageTemplateExample);
		//删除过期的
		postageTemplateDtoList.stream().forEach(postageTemplateDto -> {
			if(!postageTemplateDtos.contains(postageTemplateDto)){
				this.deleteById(postageTemplateDto.getId());
			}
		});

		//添加新增的
		postageTemplateDtos.stream().forEach(postageTemplateDto -> {
			if(!postageTemplateDtoList.contains(postageTemplateDto)){
				postageTemplateDto.setAppInfoId(AppUtils.get());
				this.save(postageTemplateDto);
			}
		});
	}
}