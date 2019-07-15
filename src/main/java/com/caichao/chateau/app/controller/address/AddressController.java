package com.caichao.chateau.app.controller.address;

import com.caichao.chateau.app.constants.enums.Validity;
import com.caichao.chateau.app.controller.response.CCResponse;
import com.caichao.chateau.app.dto.CustomerDeliveryAddressDto;
import com.caichao.chateau.app.dto.CustomerInfoDto;
import com.caichao.chateau.app.example.CustomerDeliveryAddressExample;
import com.caichao.chateau.app.mapper.CountryMapper;
import com.caichao.chateau.app.miniProgram.response.LoginResponse;
import com.caichao.chateau.app.service.CustomerDeliveryAddressService;
import com.caichao.chateau.app.service.CustomerInfoService;
import com.caichao.chateau.app.utils.CurrentUserUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	 */
	@RequestMapping("/detail/{id}")
	public CCResponse getDetail(@PathVariable Integer id) {
		CustomerDeliveryAddressDto customerDeliveryAddressDto = customerDeliveryAddressService.getById(id);
		buildDetailAddress(customerDeliveryAddressDto);
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("adressInfo", customerDeliveryAddressDto);
		return CCResponse.success(dataMap);
	}

	/**
	 * 删除地址信息
	 * @param id
	 * @return
	 */
	@RequestMapping("deleteAddress")
	public CCResponse deleteAddress(Integer id){
		customerDeliveryAddressService.deleteById(id);
		return CCResponse.success();
	}
	/**
	 * 添加收货地址
	 */
	@RequestMapping("addAddress")
	public CCResponse addAddress(@RequestBody CustomerDeliveryAddressDto customerDeliveryAddressDto) {
		LoginResponse loginResponse = CurrentUserUtils.get();
		CustomerInfoDto customerInfoDto = customerInfoService.getCustomerInfoDtoByOpenId(loginResponse.getOpenid());
		customerDeliveryAddressDto.setCustomerId(Integer.valueOf(customerInfoDto.getId() + ""));
		freshTacitlyStatus(customerDeliveryAddressDto);

		customerDeliveryAddressService.save(customerDeliveryAddressDto);
		return CCResponse.success();
	}

//	public
	/**
	 * 更新收货地址
	 * @param customerDeliveryAddressDto
	 * @return
	 */
	@RequestMapping("/updateAddress")
	public CCResponse updateAddress(@RequestBody CustomerDeliveryAddressDto customerDeliveryAddressDto) {
		if(null == customerDeliveryAddressDto.getId()) {
			throw new RuntimeException("地址id不能为空");
		}
		CustomerDeliveryAddressDto customerDeliveryAddressDto1 = customerDeliveryAddressService.getById
			(customerDeliveryAddressDto.getId());
		BeanUtils.copyProperties(customerDeliveryAddressDto, customerDeliveryAddressDto1);
		//如果当前收货地址选中，则其他选中的收货地址设置为未选中
		if(Integer.valueOf(1).equals(customerDeliveryAddressDto.getTacitly())){
			CustomerDeliveryAddressExample customerDeliveryAddressExample = new CustomerDeliveryAddressExample();
			customerDeliveryAddressExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code())
				.andTacitlyEqualTo(1);
			customerDeliveryAddressService.getList(customerDeliveryAddressExample).forEach
				(customerDeliveryAddressDto2 -> {
					customerDeliveryAddressDto2.setTacitly(0);
					customerDeliveryAddressService.update(customerDeliveryAddressDto2);
				});
		}

		customerDeliveryAddressService.update(customerDeliveryAddressDto1);

		return CCResponse.success();
	}

	/**
	 * 刷新默认状态
	 */
	private void freshTacitlyStatus(@RequestBody CustomerDeliveryAddressDto customerDeliveryAddressDto) {
		//如果当前地址设置为默认地址，则其他默认地址要设置为非默认地址
		if(Integer.valueOf(1).equals(customerDeliveryAddressDto.getTacitly())) {
			CustomerDeliveryAddressExample customerDeliveryAddressExample = new CustomerDeliveryAddressExample();
			customerDeliveryAddressExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code())
				.andTacitlyEqualTo(1);
			customerDeliveryAddressService.getList(customerDeliveryAddressExample).forEach
				(customerDeliveryAddressDto1 -> {
					customerDeliveryAddressDto1.setTacitly(0);
					customerDeliveryAddressService.update(customerDeliveryAddressDto1);
				});
		}
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

		if(!StringUtils.isEmpty(customerDeliveryAddressDto.getAddress())) {
			addressBuilder.append(customerDeliveryAddressDto.getAddress());
		}

		customerDeliveryAddressDto.setDetaiAddress(addressBuilder.toString());
	}
}
