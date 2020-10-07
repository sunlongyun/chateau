package com.chisong.green.farm.app.service.impl;

import com.chisong.green.farm.app.constants.enums.OrderStatusEnum;
import com.chisong.green.farm.app.constants.enums.UniformSpecsEnum;
import com.chisong.green.farm.app.constants.enums.Validity;
import com.chisong.green.farm.app.controller.order.request.OrderDetailReq;
import com.chisong.green.farm.app.controller.response.CCResponse;
import com.chisong.green.farm.app.dto.CartItemDto;
import com.chisong.green.farm.app.dto.CustomerDeliveryAddressDto;
import com.chisong.green.farm.app.dto.GoodsDto;
import com.chisong.green.farm.app.dto.GoodsSpecsDto;
import com.chisong.green.farm.app.dto.OrderDeliveryAddressMappingDto;
import com.chisong.green.farm.app.dto.OrderDetailDto;
import com.chisong.green.farm.app.dto.OrderInfoDto;
import com.chisong.green.farm.app.dto.PostageTemplateDto;
import com.chisong.green.farm.app.dto.SupplierDto;
import com.chisong.green.farm.app.entity.Goods;
import com.chisong.green.farm.app.entity.OrderInfo;
import com.chisong.green.farm.app.example.CartItemExample;
import com.chisong.green.farm.app.example.OrderDeliveryAddressMappingExample;
import com.chisong.green.farm.app.example.OrderDetailExample;
import com.chisong.green.farm.app.example.OrderInfoExample;
import com.chisong.green.farm.app.example.PostageTemplateExample;
import com.chisong.green.farm.app.mapper.OrderInfoMapper;
import com.chisong.green.farm.app.service.CartItemService;
import com.chisong.green.farm.app.service.CustomerDeliveryAddressService;
import com.chisong.green.farm.app.service.GoodsService;
import com.chisong.green.farm.app.service.GoodsSpecsService;
import com.chisong.green.farm.app.service.OrderDeliveryAddressMappingService;
import com.chisong.green.farm.app.service.OrderDetailService;
import com.chisong.green.farm.app.service.OrderInfoService;
import com.chisong.green.farm.app.service.PostageTemplateService;
import com.chisong.green.farm.app.service.SupplierService;
import com.chisong.green.farm.app.utils.AppUtils;
import com.chisong.green.farm.app.utils.BitUtil;
import com.chisong.green.farm.exception.BizException;
import com.github.pagehelper.PageHelper;
import com.lianshang.generator.commons.PageInfo;
import com.lianshang.generator.commons.ServiceImpl;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BinaryOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 孙龙云
 * @since 2019-06-15
 */
@Service
@Slf4j
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo, OrderInfoDto> implements
	OrderInfoService {

	@Autowired
	private OrderDetailService orderDetailService;
	@Autowired
	private CartItemService cartItemService;
	@Autowired
	private OrderDeliveryAddressMappingService orderDeliveryAddressMappingService;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private CustomerDeliveryAddressService customerDeliveryAddressService;
	@Autowired
	private PostageTemplateService postageTemplateService;
	@Autowired
	private GoodsSpecsService goodsSpecsService;
	@Autowired
	private SupplierService supplierService;

	@Override
	@Transactional
	public String createOrder(OrderInfoDto orderInfoDto, Integer addressId) {
		//1.添加订单基本信息
		orderInfoDto.setAppInfoId(AppUtils.get());
		save(orderInfoDto);
		//2.添加订单明细信息
		List<OrderDetailDto> orderDetailDtoList = orderInfoDto.getOrderDetailDtoList();
		if(CollectionUtils.isEmpty(orderDetailDtoList)) {
			throw new RuntimeException("订单明细不能为空");
		}
		long total = 0l;
		long cost = 0l;

//		List<Integer> managerIdList = new ArrayList<>();
		for(OrderDetailDto orderDetailDto : orderDetailDtoList) {
			log.info("orderDetailDto:{}", orderDetailDto);
			if(null == orderDetailDto) {
				continue;
			}
           CustomerDeliveryAddressDto customerDeliveryAddressDto =  customerDeliveryAddressService.getById(addressId);
			orderDetailDto.setOrderId(orderInfoDto.getId());
			orderDetailDto.setProvince(customerDeliveryAddressDto.getProvince());
			GoodsDto goodsDto = goodsService.getById(orderDetailDto.getGoodsId());

			total += orderDetailDto.getTotalPrice();
			GoodsSpecsDto goodsSpecsDto= goodsSpecsService.getById(orderDetailDto.getSpecsId());
			//计算成本价
			cost += goodsSpecsDto.getOriginPrice()*orderDetailDto.getNum();
			Long appInfoId = AppUtils.get();
			orderDetailDto.setAppInfoId(appInfoId);
			orderDetailService.save(orderDetailDto);
			//减少库存
			goodsService.decreaseStock(orderDetailDto.getNum(), orderDetailDto.getSpecsId());
			//如果购物项存在，则删除
			deleteCartItemList(orderDetailDto);

			//查询订单所属的管理员
			SupplierDto supplierDto = supplierService.getById(goodsDto.getSupplierId());
			orderInfoDto.setManagerId(Integer.parseInt(supplierDto.getCreatorId()+""));
//			managerIdList.add(Integer.parseInt(supplierDto.getCreatorId()+"") );
		}

		//构造managerId
//		buildManagerId(orderInfoDto, managerIdList);

		/**
		 * 订单运费
		 */
		Integer postage = computePostage(orderInfoDto.getOrderDetailDtoList());
		log.info("postage==={}", postage);
		orderInfoDto.setTotalAmount(total);
		orderInfoDto.setCostAmount(cost);
		orderInfoDto.setIncome(total-cost);
		orderInfoDto.setPostage(postage);

		//3.更新订单信息
		this.update(orderInfoDto);
		//3.保存收货地址
		CustomerDeliveryAddressDto customerDeliveryAddressDto = customerDeliveryAddressService.getById(addressId);
		OrderDeliveryAddressMappingDto orderDeliveryAddressMapping = new OrderDeliveryAddressMappingDto();
		orderDeliveryAddressMapping.setAddressId(addressId);
		orderDeliveryAddressMapping.setOrderId(orderInfoDto.getId());
		orderDeliveryAddressMapping.setOrderNo(orderInfoDto.getOrderNo());
		orderDeliveryAddressMapping.setAddress(customerDeliveryAddressDto.getDetaiAddress());
		orderDeliveryAddressMapping.setContact(customerDeliveryAddressDto.getContact());
		orderDeliveryAddressMapping.setMobile(customerDeliveryAddressDto.getMobile());
		Long appInfoId = AppUtils.get();
		orderDeliveryAddressMapping.setAppInfoId(appInfoId);
		orderDeliveryAddressMappingService.save(orderDeliveryAddressMapping);


		return orderInfoDto.getOrderNo();
	}

	/**
	 * 构造managerId
	 * @param orderInfoDto
	 * @param managerIdList
	 */
	private void buildManagerId(OrderInfoDto orderInfoDto, List<Integer> managerIdList) {
//		AtomicInteger managerIds = new AtomicInteger(0);
//		managerIdList.stream().forEach(managerId->{
//			int x = managerIds.get();
//			x = BitUtil.setOneAtIndex(x, managerId);
//			managerIds.set(x);
//		});
		orderInfoDto.setManagerId(managerIdList.get(0));
	}

	/**
	 *清空购物车该商品项
	 * @param orderDetailDto
	 */
	private void deleteCartItemList(OrderDetailDto orderDetailDto) {
		CartItemExample cartItemExample = new CartItemExample();
		cartItemExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code()).andGoodsIdEqualTo(orderDetailDto.getGoodsId());
		List<CartItemDto> cartItemDtoList = cartItemService.getList(cartItemExample);
		if(!CollectionUtils.isEmpty(cartItemDtoList)) {
			cartItemDtoList.forEach(cartItemDto -> {
				cartItemService.deleteById(cartItemDto.getId());
			});
		}
	}

	@Override
	public OrderInfoDto getOrderByNo(String orderNo) {
		OrderInfoDto orderInfoDto = getOrderInfoDto(orderNo);
		//构建订单详情列表
		buildOrderDetails(orderInfoDto);
	    //构建地址
		buildAddress(orderInfoDto);

		return orderInfoDto;
	}

	/**
	 * 订单基本信息
	 * @param orderNo
	 * @return
	 */
	private OrderInfoDto getOrderInfoDto(String orderNo) {
		OrderInfoDto orderInfoDto = null;
		OrderInfoExample orderInfoExample = new OrderInfoExample();
		orderInfoExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code()).andOrderNoEqualTo(orderNo);
		List<OrderInfoDto> orderInfoDtoList = getList(orderInfoExample);
		if(!CollectionUtils.isEmpty(orderInfoDtoList)) {
			orderInfoDto = orderInfoDtoList.get(0);
		}
		return orderInfoDto;
	}

	private void buildOrderDetails(OrderInfoDto orderInfoDto) {
		//查询订单详情
		OrderDetailExample orderDetailExample = new OrderDetailExample();
		orderDetailExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code())
			.andOrderNoEqualTo(orderInfoDto.getOrderNo()).andOrderIdEqualTo(orderInfoDto.getId());

		List<OrderDetailDto> orderDetailDtos = orderDetailService.getList(orderDetailExample);
		orderInfoDto.setOrderDetailDtoList(orderDetailDtos);
	}

	private void buildAddress(OrderInfoDto orderInfoDto) {
		//查询收货地址
		OrderDeliveryAddressMappingExample orderDeliveryAddressMappingExample =
			new OrderDeliveryAddressMappingExample();
		orderDeliveryAddressMappingExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code())
			.andOrderNoEqualTo(orderInfoDto.getOrderNo());
		OrderDeliveryAddressMappingDto orderDeliveryAddressMappingDto =
			orderDeliveryAddressMappingService.getList(orderDeliveryAddressMappingExample).stream().findFirst().get();
		log.info("orderDeliveryAddressMappingDto: {}", orderDeliveryAddressMappingDto);
		orderInfoDto.setOrderDeliveryAddressMappingDto(orderDeliveryAddressMappingDto);
		orderInfoDto.setUserAddress(orderDeliveryAddressMappingDto.getAddress());
		orderInfoDto.setUserMobile(orderDeliveryAddressMappingDto.getMobile());
		orderInfoDto.setUserName(orderDeliveryAddressMappingDto.getContact());

		CustomerDeliveryAddressDto addressDto =
			customerDeliveryAddressService.getById(orderDeliveryAddressMappingDto.getAddressId());
		log.info("addressDto:{}", addressDto);
		orderInfoDto.setAddress(addressDto);
	}

	@Override
	public OrderInfoDto getOrderById(Long id) {
		OrderInfoDto orderInfoDto = getById(id);
		return getOrderByNo(orderInfoDto.getOrderNo());
	}

	/**
	 * 查询总 运费
	 * @param orderDetailReqList
	 * @return
	 */
	private long getTotalPostFee(List<OrderDetailDto> orderDetailReqList) {
		log.info("orderDetailReqList:{}", orderDetailReqList);
		return (long) orderDetailReqList.stream().map(orderDetailReq->{
			Long goodsId = orderDetailReq.getGoodsId();
			//查询运费模板
			log.info("orderDetailReq:{}", orderDetailReq);
			PostageTemplateExample postageTemplateExample = new PostageTemplateExample();
			postageTemplateExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code())
				.andGoodsIdEqualTo(goodsId).andProvincesLike("%"+orderDetailReq.getProvince().trim()+"%");
			postageTemplateExample.setOrderByClause("weight desc, id desc");
			Optional<PostageTemplateDto> postageTemplateDtoOptional =
				postageTemplateService.getList(postageTemplateExample).stream().findFirst();
			if(!postageTemplateDtoOptional.isPresent()){
				throw new RuntimeException("抱歉，该地区暂不发货");
			}

			GoodsSpecsDto goodsSpecsDto = goodsSpecsService.getById(orderDetailReq.getSpecsId());
			Long detailPrice  = Long.parseLong(goodsSpecsDto.getPrice()+"") ;
			if(goodsSpecsDto.getPromote() ==1){
				 detailPrice = Long.parseLong( goodsSpecsDto.getPromotionPrice()+"") ;;
			}


			PostageTemplateDto postageTemplateDto = postageTemplateDtoOptional.get();
			//包邮
			if((null != postageTemplateDto.getFreeNum() && orderDetailReq.getNum() >= postageTemplateDto.getFreeNum())
				|| (null !=postageTemplateDto.getFreeTotalAmount()
					&& detailPrice * orderDetailReq.getNum() >= postageTemplateDto.getFreeTotalAmount())){
				log.info("包邮--------------");
				return  0;
			}

			//运费阶梯递增
			if(null != postageTemplateDto.getIncUnitNum()){
				if(postageTemplateDto.getIncUnitNum() <=0 || orderDetailReq.getNum() == 1){
					return Integer.parseInt( postageTemplateDtoOptional.get().getAmount()+"") ;
				}else{
					return (((orderDetailReq.getNum()-1)/postageTemplateDto.getIncUnitNum())+1) * postageTemplateDtoOptional.get().getAmount();
				}
			}

			//每件商品增加一次运费
			return orderDetailReq.getNum() * postageTemplateDtoOptional.get().getAmount();
		}).reduce(0, (a, b) -> a+b);
	}
	@Override
	public Integer computePostage(List<OrderDetailDto> orderDetailReqList) {
		return Integer.parseInt( getTotalPostFee(orderDetailReqList)+"");
	}

	@Override
	public List<OrderInfoDto> getOrderList(Map<String, Object> params) {
		List<OrderInfo> orderInfos = this.baseMapper.getOrderList(params);

		return copyList(orderInfos, OrderInfoDto.class);
	}

	@Override
	public PageInfo<?> getOrderInfoPageInfo(int pageNo, int pageSize, Map<String, Object> params) {
		PageHelper.startPage(pageNo, pageSize);
		List<OrderInfo> orderInfos = this.baseMapper.getOrderList(params);
		PageInfo pageInfo = PageInfo.getPageInfo(orderInfos);


		List<OrderInfoDto> orderInfoDtoList = copyList(orderInfos, OrderInfoDto.class);
		pageInfo.setDataList(orderInfoDtoList);

		orderInfoDtoList.stream().forEach(orderInfoDto -> {
			//构建收货地址
			buildAddress(orderInfoDto);

			//结算之前1个小时之后，不可执行退款
			LocalDateTime monthBefore =    LocalDateTime.now().plus(-1, ChronoUnit.HOURS);
			Date date =   Date.from(monthBefore.atZone(ZoneId.systemDefault()).toInstant());
			boolean canRefund = null !=  orderInfoDto.getFeeTransferTime() && orderInfoDto.getFeeTransferTime().after(date)
				&& orderInfoDto.getRefundAmount() < orderInfoDto.getPayedAmount();

			orderInfoDto.setCanRefund(canRefund);
		});
		return  pageInfo;
	}

}