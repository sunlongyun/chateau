package com.caichao.chateau.app.controller.address;

import com.caichao.chateau.app.constants.enums.Validity;
import com.caichao.chateau.app.controller.response.CCResponse;
import com.caichao.chateau.app.dto.CustomerDeliveryAddressDto;
import com.caichao.chateau.app.dto.CustomerInfoDto;
import com.caichao.chateau.app.example.CustomerDeliveryAddressExample;
import com.caichao.chateau.app.miniProgram.response.LoginResponse;
import com.caichao.chateau.app.service.CustomerDeliveryAddressService;
import com.caichao.chateau.app.service.CustomerInfoService;
import com.caichao.chateau.app.utils.CurrentUserUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 *
 * @AUTHOR 孙龙云
 * @date 2019-06-21 21:45
 */
@RequestMapping("/address")
@RestController
public class AddressController {

	@Autowired
	private CustomerInfoService customerInfoService;
	@Autowired
	private CustomerDeliveryAddressService customerDeliveryAddressService;

	/**
	 * 获取收获地址列表
	 */
	@RequestMapping("list")
	public CCResponse getList() {
		LoginResponse loginResponse = CurrentUserUtils.get();
		CustomerInfoDto customerInfoDto = customerInfoService.getCustomerInfoDtoByOpenId(loginResponse.getOpenid());

		CustomerDeliveryAddressExample customerDeliveryAddressExample = new CustomerDeliveryAddressExample();
		customerDeliveryAddressExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code())
			.andCustomerIdEqualTo(Integer.valueOf(customerInfoDto.getId() + ""));

		List<CustomerDeliveryAddressDto> list = customerDeliveryAddressService.getList(customerDeliveryAddressExample);
		list.forEach(getCustomerDeliveryAddressDtoConsumer());
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("list", list);

		return CCResponse.success(dataMap);
	}

	/**
	 * 获取地址详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/detail/{id}")
	public CCResponse getDetail(@PathVariable Integer id){
		CustomerDeliveryAddressDto customerDeliveryAddressDto = customerDeliveryAddressService.getById(id);
		buildDetailAddress(customerDeliveryAddressDto);
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("adressInfo",customerDeliveryAddressDto);
		return CCResponse.success(dataMap);
	}

	private Consumer<CustomerDeliveryAddressDto> getCustomerDeliveryAddressDtoConsumer() {
		return customerDeliveryAddressDto -> {
			buildDetailAddress(customerDeliveryAddressDto);
		};
	}

	private void buildDetailAddress(CustomerDeliveryAddressDto customerDeliveryAddressDto) {
		StringBuilder addressBuilder = new StringBuilder();
		if(!StringUtils.isEmpty(customerDeliveryAddressDto.getCountry())) {
			addressBuilder.append(customerDeliveryAddressDto.getCountry());
		}
		if(!StringUtils.isEmpty(customerDeliveryAddressDto.getProvince())) {
			addressBuilder.append(customerDeliveryAddressDto.getProvince());
		}

		if((StringUtils.isEmpty(customerDeliveryAddressDto.getProvince()) || !customerDeliveryAddressDto.getProvince
			().equals(customerDeliveryAddressDto.getCity())) && !StringUtils.isEmpty(customerDeliveryAddressDto
			.getCity())) {
			addressBuilder.append(customerDeliveryAddressDto.getCity());
		}

		if(!StringUtils.isEmpty(customerDeliveryAddressDto.getAddress())){
			addressBuilder.append(customerDeliveryAddressDto.getAddress());
		}

		customerDeliveryAddressDto.setDetaiAddress(addressBuilder.toString());
	}
}
