package com.caichao.chateau.app.controller.customer;

import com.caichao.chateau.app.controller.customer.request.FreshCustomerReq;
import com.caichao.chateau.app.controller.response.CCResponse;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 *
 * @AUTHOR 孙龙云
 * @date 2019-06-09 15:44
 */
@RestController
public class CustomerController {

	public CCResponse freshCustomerInfo(FreshCustomerReq freshCustomerReq){
		return CCResponse.success();
	}
}
