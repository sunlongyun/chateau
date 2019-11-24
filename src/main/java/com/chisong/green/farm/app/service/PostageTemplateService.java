package com.chisong.green.farm.app.service;

import com.lianshang.generator.commons.IService;
import com.chisong.green.farm.app.dto.PostageTemplateDto;
import java.util.List;

/**
 * <p>
 * 运费模板 服务类
 * </p>
 *
 * @author 孙龙云
 * @since 2019-11-10
 */
public interface PostageTemplateService extends IService<PostageTemplateDto> {

	/**
	 * 为商品生成默认模板
	 * @param goodsId
	 */
	public void saveDefaultTemplate(Long goodsId);

	/**
	 * 保存模板设置，同一商品下的模板
	 * @param postageTemplateDtos
	 */
	public void savePostageTemplateDtoList(List<PostageTemplateDto> postageTemplateDtos);
}
