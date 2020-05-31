package com.chisong.green.farm.app.service.impl;

import com.chisong.green.farm.app.constants.enums.Validity;
import com.chisong.green.farm.app.controller.goods.request.PageQueryReq;
import com.chisong.green.farm.app.dto.GoodsDto;
import com.chisong.green.farm.app.dto.GoodsSpecsDto;
import com.chisong.green.farm.app.dto.GoodsTailImagesDto;
import com.chisong.green.farm.app.dto.GoodsTopImagesDto;
import com.chisong.green.farm.app.dto.GoodsTypeDto;
import com.chisong.green.farm.app.entity.Goods;
import com.chisong.green.farm.app.entity.GoodsSpecs;
import com.chisong.green.farm.app.example.GoodsSpecsExample;
import com.chisong.green.farm.app.example.GoodsTailImagesExample;
import com.chisong.green.farm.app.example.GoodsTopImagesExample;
import com.chisong.green.farm.app.mapper.GoodsMapper;
import com.chisong.green.farm.app.mapper.GoodsSpecsMapper;
import com.chisong.green.farm.app.service.GoodsService;
import com.chisong.green.farm.app.service.GoodsSpecsService;
import com.chisong.green.farm.app.service.GoodsTailImagesService;
import com.chisong.green.farm.app.service.GoodsTopImagesService;
import com.chisong.green.farm.app.service.GoodsTypeService;
import com.chisong.green.farm.app.service.PostageTemplateService;
import com.github.pagehelper.PageHelper;
import com.lianshang.generator.commons.PageInfo;
import com.lianshang.generator.commons.ServiceImpl;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods, GoodsDto> implements GoodsService {

	@Autowired
	private GoodsTopImagesService goodsTopImagesService;

	@Autowired
	private GoodsTailImagesService goodsTailImagesService;

	@Autowired
	private GoodsSpecsService goodsSpecsService;
	@Autowired
	private PostageTemplateService postageTemplateService;
	@Autowired
	private GoodsSpecsMapper goodsSpecsMapper;

	@Autowired
	private GoodsTypeService goodsTypeService;

	@Override
	public PageInfo<GoodsDto> getGoodsInfo(PageQueryReq pageQueryReq) {
		if(pageQueryReq.getHot() == 1) {
			pageQueryReq.setPageSize(6);
		}

		PageHelper.startPage(pageQueryReq.getPageNo(), pageQueryReq.getPageSize());
		List<Goods> goodsDtoList = this.baseMapper.getGoodsList(pageQueryReq);
		PageInfo pageInfo = PageInfo.getPageInfo(goodsDtoList);
		List<GoodsDto> goodsDtos = copyList(goodsDtoList, GoodsDto.class);

		pageInfo.setDataList(goodsDtos);
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
		setSpecsPrice(goodsDto, goodsSpecsDtos);

		goodsDto.setSpecsDtoList(goodsSpecsDtos);
		return goodsDto;
	}

	/**
	 * 设置规格价格
	 *
	 * @param goodsDto
	 * @param goodsSpecsDtos
	 */
	private void setSpecsPrice(GoodsDto goodsDto, List<GoodsSpecsDto> goodsSpecsDtos) {

			Optional<GoodsSpecsDto> firstSpecsDtoOptional = null;
			firstSpecsDtoOptional = goodsSpecsDtos.stream().filter(goodsSpecsDto -> goodsSpecsDto.getIsShow() == 1)
				.sorted(Comparator.comparing(GoodsSpecsDto::getId)).findFirst();

			if(!firstSpecsDtoOptional.isPresent()) {
				firstSpecsDtoOptional = goodsSpecsDtos.stream()
					.sorted(Comparator.comparing(GoodsSpecsDto::getId)).findFirst();
			}


			firstSpecsDtoOptional.get().setSelected(1);
			int price =
				firstSpecsDtoOptional.get().getPromotionPrice() != null
					&& goodsDto.isPromote() && firstSpecsDtoOptional.get().getPromote() == 1
				? firstSpecsDtoOptional.get().getPromotionPrice() : firstSpecsDtoOptional.get().getPrice();

			goodsDto.setSpecsId(firstSpecsDtoOptional.get().getId());
			goodsDto.setSpecsPrice(Long.parseLong(price + ""));

	}

	@Override
	public int decreaseStock(int stock, Long specsId) {

	    GoodsSpecs goodsSpecs =  goodsSpecsMapper.selectById(specsId);
	    log.info("goodsSpecs=={}", goodsSpecs);
		int r  = goodsSpecsMapper.decreaseStock(stock, specsId);
		Goods goods = this.baseMapper.selectById(goodsSpecs.getGoodsId());
		if(r==0){
			String tips = "您购买的商品【"+goods.getTitle()+"】,规格【"+goodsSpecs.getName()+"】库存不足";
			throw new RuntimeException(tips);
		}
		if(goods.getSalesNum() == null){
			goods.setSalesNum(0);
		}
		goods.setSalesNum(goods.getSalesNum() + stock);
		update(entityToDto(goods));
		return r;
	}

	@Override
	@Transactional
	public void saveOrUpdateGoods(GoodsDto goodsDto) {
		if(null == goodsDto.getSpecsDtoList() || goodsDto.getSpecsDtoList().isEmpty()) {
			throw new RuntimeException("请添加商品规格");
		}
		//商品价格默认是规格中最低的价格，
		//如果有商品参加活动，则默认是参加活动中的最低价格
		Integer price = goodsDto.getSpecsDtoList().stream().sorted(
			Comparator.comparingInt(GoodsSpecsDto::getPrice)).findFirst().get().getPrice();
		goodsDto.setPrice(Long.parseLong(price+""));

		Optional<GoodsSpecsDto> goodsSpecsDtoOptional =
			goodsDto.getSpecsDtoList().stream().filter(specsDto -> specsDto.getPromote() == 1 && specsDto.getIsShow() ==1).findFirst();
		if(!goodsSpecsDtoOptional.isPresent()){
			goodsSpecsDtoOptional = goodsDto.getSpecsDtoList().stream().filter(specsDto -> specsDto.getPromote() == 1)
				.sorted(Comparator.comparingInt(GoodsSpecsDto::getPromotionPrice)).findFirst();
		}
		if(!goodsSpecsDtoOptional.isPresent()){
			goodsSpecsDtoOptional = goodsDto.getSpecsDtoList().stream().findFirst();
		}

		goodsSpecsDtoOptional.ifPresent(specs->{
			goodsDto.setPromotePrice(Long.parseLong(specs.getPromotionPrice()+""));
			goodsDto.setPrice(Long.parseLong(specs.getPrice()+""));
		});


		//更新分类名称
		GoodsTypeDto firstGoodsType =  goodsTypeService.getById(goodsDto.getFirstTypeId());
		GoodsTypeDto secondeGoodsType =  goodsTypeService.getById(goodsDto.getTypeId());
		if(null == firstGoodsType || null == secondeGoodsType){
			throw new RuntimeException("商品一级分类和二级分类都不能为空");
		}
		goodsDto.setTypeName(firstGoodsType.getName()+" — "+secondeGoodsType.getName());

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
				if(null == newGoodsSpecsDto.getPromote()) {
					newGoodsSpecsDto.setPromote(0);
				}
				if(null == newGoodsSpecsDto.getPrice()) {
					throw new RuntimeException("规格【"+newGoodsSpecsDto.getName()+"】售价不能为空");
				}
				if(null == newGoodsSpecsDto.getOriginPrice()) {
					throw new RuntimeException("规格【"+newGoodsSpecsDto.getName()+"】进价不能为空");
				}

				newGoodsSpecsDto.setPromote(newGoodsSpecsDto.getPromote());
				newGoodsSpecsDto.setPrice(newGoodsSpecsDto.getPrice());
				newGoodsSpecsDto.setOriginPrice(newGoodsSpecsDto.getOriginPrice());
				newGoodsSpecsDto.setPromotionPrice(newGoodsSpecsDto.getPromotionPrice());
				goodsSpecsService.save(newGoodsSpecsDto);
			}
		});
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