package com.chisong.green.farm.app.service.impl;

import com.chisong.green.farm.app.constants.enums.UniformSpecsEnum;
import com.chisong.green.farm.app.constants.enums.Validity;
import com.chisong.green.farm.app.controller.goods.request.PageQueryReq;
import com.chisong.green.farm.app.dto.GoodsDto;
import com.chisong.green.farm.app.dto.GoodsSpecsDto;
import com.chisong.green.farm.app.dto.GoodsTailImagesDto;
import com.chisong.green.farm.app.dto.GoodsTopImagesDto;
import com.chisong.green.farm.app.entity.Goods;
import com.chisong.green.farm.app.example.GoodsSpecsExample;
import com.chisong.green.farm.app.example.GoodsTailImagesExample;
import com.chisong.green.farm.app.example.GoodsTopImagesExample;
import com.chisong.green.farm.app.mapper.GoodsMapper;
import com.chisong.green.farm.app.service.GoodsService;
import com.chisong.green.farm.app.service.GoodsSpecsService;
import com.chisong.green.farm.app.service.GoodsTailImagesService;
import com.chisong.green.farm.app.service.GoodsTopImagesService;
import com.chisong.green.farm.app.service.PostageTemplateService;
import com.github.pagehelper.PageHelper;
import com.lianshang.generator.commons.PageInfo;
import com.lianshang.generator.commons.ServiceImpl;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * <p>
 * 酒水 服务实现类
 * </p>
 *
 * @author 孙龙云
 * @since 2019-10-19
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods, GoodsDto> implements GoodsService {

	@Autowired
	private GoodsTopImagesService goodsTopImagesService;

	@Autowired
	private GoodsTailImagesService goodsTailImagesService;

	@Autowired
	private GoodsSpecsService goodsSpecsService;
	@Autowired
	private PostageTemplateService postageTemplateService;

	@Override
	public PageInfo<GoodsDto> getGoodsInfo(PageQueryReq pageQueryReq) {
		if(pageQueryReq.getHot() == 1) {
			pageQueryReq.setPageSize(6);
		}
		PageHelper.startPage(pageQueryReq.getPageNo(), pageQueryReq.getPageSize());
		List<Goods> goodsDtoList = this.baseMapper.getGoodsList(pageQueryReq);
		PageInfo pageInfo = PageInfo.getPageInfo(goodsDtoList);

		pageInfo.setDataList(copyList(goodsDtoList, GoodsDto.class));
		return pageInfo;
	}


	@Override
	public GoodsDto getDetailById(Long id) {
		GoodsDto goodsDto = getById(id);

		//商品详情滚动轮播图
		GoodsTopImagesExample goodsTopImagesExample = new GoodsTopImagesExample();
		goodsTopImagesExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code()).andGoodsIdEqualTo(id);
		List<String> topImgList = goodsTopImagesService.getList(goodsTopImagesExample).stream()
			.map(GoodsTopImagesDto::getImageUrl).collect(Collectors.toList());
		goodsDto.setTopImages(topImgList);

		//商品详情介绍图片
		GoodsTailImagesExample goodsTailImagesExample = new GoodsTailImagesExample();
		goodsTailImagesExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code()).andGoodsIdEqualTo(id);
		List<String> tailImgList = goodsTailImagesService.getList(goodsTopImagesExample).stream().map(
			GoodsTailImagesDto::getImageUrl).collect(Collectors.toList());
		goodsDto.setTailImages(tailImgList);

		//非统一规格商品，规格列表
		GoodsSpecsExample goodsSpecsExample = new GoodsSpecsExample();
		goodsSpecsExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code())
			.andGoodsIdEqualTo(id);

		List<GoodsSpecsDto> goodsSpecsDtos = goodsSpecsService.getList(goodsSpecsExample);
		if(!CollectionUtils.isEmpty(goodsSpecsDtos)){
			GoodsSpecsDto goodsSpecsDto =  goodsSpecsDtos.get(0);
			goodsSpecsDto.setSelected(1);
			goodsDto.setSpecsPrice(Long.parseLong(""+goodsSpecsDto.getPrice()));
			goodsDto.setSpecsId(goodsSpecsDto.getId());
		}else{
			goodsDto.setSpecsPrice(goodsDto.getPrice());
		}

        goodsDto.setSpecsDtoList(goodsSpecsDtos);
		return goodsDto;
	}

	@Override
	public int decreaseStock(int stock, Long id) {
		int r  = this.baseMapper.decreaseStock(stock,id);
		if(r==0){
			throw new RuntimeException("商品库存不足,编号【"+id+"】");
		}
		return r;
	}

	@Override
	@Transactional
	public void saveOrUpdateGoods(GoodsDto goodsDto) {
		if(null == goodsDto.getId()) {
			save(goodsDto);
		} else {
			update(goodsDto);
		}
		//如果不存在模板，则添加默认模板
		postageTemplateService.saveDefaultTemplate(goodsDto.getId());

		//商品详情轮播图
		handleGoodsTopImages(goodsDto);
		//商品明细介绍图
		handleGoodsTailImages(goodsDto);

		//规格列表
		handleGoodsSpecs(goodsDto);
	}

	/**
	 * 处理规格列表
	 *
	 * @param goodsDto
	 */
	private void handleGoodsSpecs(GoodsDto goodsDto) {
		GoodsSpecsExample goodsSpecsExample = new GoodsSpecsExample();
		goodsSpecsExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code())
			.andGoodsIdEqualTo(goodsDto.getId());
		List<GoodsSpecsDto> newGoodsSpecsDtos = goodsDto.getSpecsDtoList();
		List<GoodsSpecsDto> goodsSpecsDtos = goodsSpecsService.getList(goodsSpecsExample);

		//统一规格，删除商品下的所有规格列表
		if(goodsDto.getUniformSpecs() == UniformSpecsEnum.YES.code()) {
			goodsSpecsDtos.stream().forEach(goodsSpecsDto -> {
				goodsSpecsService.deleteById(goodsSpecsDto.getId());
			});
			return;
		}

		//非统一规格，删除多余的规格，添加新增的规格
		if(goodsDto.getUniformSpecs() == UniformSpecsEnum.NO.code()) {

			//删除不用的规格
			goodsSpecsDtos.stream().forEach(goodsSpecsDto -> {
				if(!newGoodsSpecsDtos.contains(goodsSpecsDto)) {
					goodsSpecsService.deleteById(goodsSpecsDto.getId());
				}
			});
			//添加新增的规格
			newGoodsSpecsDtos.stream().forEach(newGoodsSpecsDto -> {
				if(!goodsSpecsDtos.contains(newGoodsSpecsDto)) {
					newGoodsSpecsDto.setGoodsId(goodsDto.getId());
					goodsSpecsService.save(newGoodsSpecsDto);
				}
			});
		}
	}

	/**
	 * 商品头部滚动图片
	 *
	 * @param goodsDto
	 */
	private void handleGoodsTopImages(GoodsDto goodsDto) {
		final List<String> goodsTops = goodsDto.getTopImages();

		GoodsTopImagesExample goodsTopImagesExample = new GoodsTopImagesExample();
		goodsTopImagesExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code())
			.andGoodsIdEqualTo(goodsDto.getId());
		List<GoodsTopImagesDto> goodsTopImagesDtos = goodsTopImagesService.getList(goodsTopImagesExample);

		goodsTopImagesDtos.stream().filter(goodsTopImagesDto -> {
			return !goodsTops.contains(goodsTopImagesDto.getImageUrl());
		}).forEach(goodsTopImagesDto -> {
			goodsTopImagesService.deleteById(goodsTopImagesDto.getId());
		});

		//goodsTops 删除已经存在的
		goodsTopImagesDtos.stream().forEach(goodsTopImagesDto -> {
			if(goodsTops.contains(goodsTopImagesDto.getImageUrl())) {
				goodsTops.remove(goodsTopImagesDto.getImageUrl());
			}
		});
		//goodsTops 剩余的是需要添加的
		goodsTops.stream().forEach(imageUrl -> {
			GoodsTopImagesDto goodsTopImagesDto = new GoodsTopImagesDto();
			goodsTopImagesDto.setGoodsId(goodsDto.getId());
			goodsTopImagesDto.setImageUrl(imageUrl);
			goodsTopImagesService.save(goodsTopImagesDto);
		});
	}


	/**
	 * 处理商品介绍图片
	 *
	 * @param goodsDto
	 */
	private void handleGoodsTailImages(GoodsDto goodsDto) {
		final List<String> goodsTails = goodsDto.getTailImages();

		GoodsTailImagesExample goodsTailImagesExample = new GoodsTailImagesExample();
		goodsTailImagesExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code())
			.andGoodsIdEqualTo(goodsDto.getId());
		List<GoodsTailImagesDto> goodsTailImagesDtos = goodsTailImagesService.getList(goodsTailImagesExample);

		goodsTailImagesDtos.stream().filter(goodsTailImagesDto -> {
			return !goodsTails.contains(goodsTailImagesDto.getImageUrl());
		}).forEach(goodsTailImagesDto -> {
			goodsTailImagesService.deleteById(goodsTailImagesDto.getId());
		});

		//goodsTails 删除已经存在的
		goodsTailImagesDtos.stream().forEach(goodsTailImagesDto -> {
			if(goodsTails.contains(goodsTailImagesDto.getImageUrl())) {
				goodsTails.remove(goodsTailImagesDto.getImageUrl());
			}
		});
		//goodsTails 剩余的是需要添加的
		goodsTails.stream().forEach(imageUrl -> {
			GoodsTailImagesDto goodsTailImagesDto = new GoodsTailImagesDto();
			goodsTailImagesDto.setGoodsId(goodsDto.getId());
			goodsTailImagesDto.setImageUrl(imageUrl);
			goodsTailImagesService.save(goodsTailImagesDto);
		});
	}

}