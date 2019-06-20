package com.caichao.chateau.app.controller.cart;

import com.caichao.chateau.app.constants.enums.Validity;
import com.caichao.chateau.app.controller.response.CCResponse;
import com.caichao.chateau.app.controller.response.enums.ResponseCodeEnum;
import com.caichao.chateau.app.dto.CartItemDto;
import com.caichao.chateau.app.dto.CountryChateauBeverageDto;
import com.caichao.chateau.app.dto.CustomerInfoDto;
import com.caichao.chateau.app.dto.ShoppingCartDto;
import com.caichao.chateau.app.example.CartItemExample;
import com.caichao.chateau.app.example.ShoppingCartExample;
import com.caichao.chateau.app.miniProgram.response.LoginResponse;
import com.caichao.chateau.app.service.CartItemService;
import com.caichao.chateau.app.service.CountryChateauBeverageService;
import com.caichao.chateau.app.service.CustomerInfoService;
import com.caichao.chateau.app.service.ShoppingCartService;
import com.caichao.chateau.app.utils.CurrentUserUtils;
import com.caichao.chateau.exception.BizException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
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
	private CountryChateauBeverageService countryChateauBeverageService;

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

	public CCResponse addCartItem(Integer cartItemId, Integer num, Long beverageId) {
		ShoppingCartDto shoppingCartDto = getShoppingCartDto();
		if(null == shoppingCartDto) {
			throw new BizException(ResponseCodeEnum.FAIL.code(), "购物车获取失败");
		}
		CartItemDto cartItemDto = cartItemService.getById(cartItemId);
		if(null != cartItemDto) {//购物项不存在则添加
			cartItemDto = new CartItemDto();
			cartItemDto.setNum(num);
			cartItemDto.setCartId(shoppingCartDto.getId());
			CountryChateauBeverageDto countryChateauBeverageDto = countryChateauBeverageService.getById(beverageId);
			cartItemDto.setBeverageId(beverageId);
			cartItemDto.setPrice(countryChateauBeverageDto.getPrice());
			cartItemDto.setMinPicUrl(countryChateauBeverageDto.getMinPicUrl());
			cartItemDto.setTitle(countryChateauBeverageDto.getTitle());
			cartItemService.save(cartItemDto);
		} else {//更新购物项
			cartItemDto.setNum(num);
			cartItemService.update(cartItemDto);
		}
		return CCResponse.success();
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
