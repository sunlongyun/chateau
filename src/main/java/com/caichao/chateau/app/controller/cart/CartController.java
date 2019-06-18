package com.caichao.chateau.app.controller.cart;

import com.caichao.chateau.app.constants.enums.Validity;
import com.caichao.chateau.app.controller.response.CCResponse;
import com.caichao.chateau.app.controller.response.enums.ResponseCodeEnum;
import com.caichao.chateau.app.dto.CartItemDto;
import com.caichao.chateau.app.dto.CustomerInfoDto;
import com.caichao.chateau.app.dto.ShoppingCartDto;
import com.caichao.chateau.app.example.CartItemExample;
import com.caichao.chateau.app.example.ShoppingCartExample;
import com.caichao.chateau.app.miniProgram.response.LoginResponse;
import com.caichao.chateau.app.service.CartItemService;
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
