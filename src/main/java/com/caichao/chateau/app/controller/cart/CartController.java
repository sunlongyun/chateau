package com.caichao.chateau.app.controller.cart;

import com.caichao.chateau.app.constants.enums.Validity;
import com.caichao.chateau.app.controller.response.CCResponse;
import com.caichao.chateau.app.controller.response.enums.ResponseCodeEnum;
import com.caichao.chateau.app.dto.CartItemDto;
import com.caichao.chateau.app.dto.CustomerInfoDto;
import com.caichao.chateau.app.dto.GoodsDto;
import com.caichao.chateau.app.dto.ShoppingCartDto;
import com.caichao.chateau.app.example.CartItemExample;
import com.caichao.chateau.app.example.ShoppingCartExample;
import com.caichao.chateau.app.miniProgram.response.LoginResponse;
import com.caichao.chateau.app.service.CartItemService;
import com.caichao.chateau.app.service.CustomerInfoService;
import com.caichao.chateau.app.service.GoodsService;
import com.caichao.chateau.app.service.ShoppingCartService;
import com.caichao.chateau.app.utils.CurrentUserUtils;
import com.caichao.chateau.exception.BizException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 * 购物车管理controller
 *
 * @AUTHOR 孙龙云
 * @date 2019-06-18 20:59
 */
@RequestMapping("cart")
@RestController
public class CartController {

	@Autowired
	private ShoppingCartService shoppingCartService;
	@Autowired
	private CartItemService cartItemService;
	@Autowired
	private CustomerInfoService customerInfoService;
	@Autowired
	private GoodsService goodsService;

	/**
	 * 批量删除购物车项
	 * @param cartItemIdList
	 * @return
	 */
	@RequestMapping("/batchDeleteItem")
	public CCResponse batchDelete(@RequestBody List<Integer> cartItemIdList){
		if(!CollectionUtils.isEmpty(cartItemIdList)){
			cartItemIdList.forEach(cartItemId->{
				cartItemService.deleteById(cartItemId);
			});
		}
		return CCResponse.success();
	}
	/**
	 * 我的购物车
	 */
	@RequestMapping("/myCart")
	public CCResponse myCart() {
		ShoppingCartDto shoppingCartDto = getShoppingCartDto();
		if(null == shoppingCartDto) {
			throw new BizException(ResponseCodeEnum.FAIL.code(), "购物车获取失败");
		}
		CartItemExample cartItemExample = new CartItemExample();
		cartItemExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code()).andCartIdEqualTo(shoppingCartDto
			.getId());
		List<CartItemDto> cartItemDtoList = cartItemService.getList(cartItemExample);

		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("id", shoppingCartDto.getId());
		dataMap.put("cartItemList", cartItemDtoList);

		return CCResponse.success(dataMap);
	}

	/**
	 * 更新购物车购物项数量
	 */
	@RequestMapping("/updateItem")
	public CCResponse updateCartItem(Integer cartItemId, Integer num) {
		CartItemDto cartItemDto = cartItemService.getById(cartItemId);
		if(checkItem(cartItemDto)) {
			return CCResponse.fail("购物项不存在");
		}

		cartItemDto.setNum(num);
		cartItemDto.setTotalPrice(cartItemDto.getPrice() * num);
		cartItemService.update(cartItemDto);
		return CCResponse.success();
	}

	private boolean checkItem(CartItemDto cartItemDto) {
		if(null == cartItemDto) {
			return true;
		}
		ShoppingCartDto shoppingCartDto = getShoppingCartDto();
		if(null == shoppingCartDto) {
			throw new BizException(ResponseCodeEnum.FAIL.code(), "购物车获取失败");
		}
		if(!cartItemDto.getCartId().equals(shoppingCartDto.getId())) {
			throw new BizException(ResponseCodeEnum.FAIL.code(), "更新内容不属于当前用户的购物车");
		}
		return false;
	}

	/**
	 * 删除购物项
	 */
	@RequestMapping("/deleteItem")
	public CCResponse deleteItem(Integer cartItemId) {
		CartItemDto cartItemDto = cartItemService.getById(cartItemId);
		if(checkItem(cartItemDto)) {
			return CCResponse.fail("购物项不存在");
		}
		cartItemService.deleteById(cartItemId);
		return CCResponse.success();
	}

	/**
	 * 添加购物车
	 */
	@RequestMapping("addCartItem")
	public CCResponse addCartItem(Integer num, Long beverageId) {
		ShoppingCartDto shoppingCartDto = getShoppingCartDto();
		if(null == shoppingCartDto) {
			throw new BizException(ResponseCodeEnum.FAIL.code(), "购物车获取失败");
		}

		GoodsDto goodsDto = goodsService.getById(beverageId);
		if(null == goodsDto) {
			throw new RuntimeException("商品不存在");
		}
		CartItemDto cartItemDto = null;
		//先校验该商品是否应在购物车，如果已经存在，则执行添加
		CartItemExample cartItemExample = new CartItemExample();
		cartItemExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code()).andCartIdEqualTo(shoppingCartDto
			.getId()).andBeverageIdEqualTo(beverageId);

		List<CartItemDto> cartItemList = cartItemService.getList(cartItemExample);
		if(!CollectionUtils.isEmpty(cartItemList)){
			cartItemDto = cartItemList.get(0);
			cartItemDto.setNum(cartItemDto.getNum()+num);
			cartItemDto.setTotalPrice(cartItemDto.getPrice()*cartItemDto.getNum());
			cartItemService.update(cartItemDto);
		}else{
			cartItemDto = new CartItemDto();
			cartItemDto.setNum(num);
			cartItemDto.setCartId(shoppingCartDto.getId());
			cartItemDto.setBeverageId(beverageId);
			cartItemDto.setPrice(goodsDto.getPrice());
			cartItemDto.setMinPicUrl(goodsDto.getMinPicUrl());
			cartItemDto.setTitle(goodsDto.getTitle());
			cartItemDto.setTotalPrice(goodsDto.getPrice() * num);

			cartItemService.save(cartItemDto);
		}

		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("cartItemId", cartItemDto.getId());
		return CCResponse.success(dataMap);
	}

	/**
	 * 查询购物车
	 */
	private ShoppingCartDto getShoppingCartDto() {
		ShoppingCartDto shoppingCartDto = null;
		LoginResponse loginResponse = CurrentUserUtils.get();
		CustomerInfoDto customerInfoDto = customerInfoService.getCustomerInfoDtoByOpenId(loginResponse.getOpenid());

		ShoppingCartExample shoppingCartExample = new ShoppingCartExample();
		shoppingCartExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code()).andCustomerInfoIdEqualTo
			(Integer.valueOf(customerInfoDto.getId() + ""));
		List<ShoppingCartDto> shoppingCartDtoList = shoppingCartService.getList(shoppingCartExample);
		if(!CollectionUtils.isEmpty(shoppingCartDtoList)) {
			shoppingCartDto = shoppingCartDtoList.get(0);
		}
		return shoppingCartDto;
	}


}
