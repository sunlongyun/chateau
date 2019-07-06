package com.caichao.chateau.app.controller.beverage.request;

import lombok.Data;

/**
 * 描述:
 *
 * @AUTHOR 孙龙云
 * @date 2019-06-18 7:18
 */
@Data
public class PageQueryReq {

	/**
	 * 搜索关键字
	 */
	private String searchKey;
	/**
	 * 庄园id
	 */
	private String chateauId;
	/**
	 * 国家id
	 */
	private Integer countryId;
	/**
	 * 供应商id
	 */
	private Integer supplierId;
	/**
	 * 排序字段
	 */
	private String sortKey;
	/**
	 * 排序方式
	 */
	private String sortType ="asc";
	/**
	 * 当前页码
	 */
	private Integer pageNo=1;
	/**
	 * 每页显示数量
	 */
	private Integer pageSize = 10;

}
