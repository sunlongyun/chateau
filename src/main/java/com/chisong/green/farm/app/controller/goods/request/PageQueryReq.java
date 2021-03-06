package com.chisong.green.farm.app.controller.goods.request;

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
	 * 是否是热门推荐
	 */
	private Integer hot=0;
	/**
	 * 搜索关键字
	 */
	private String searchKey;
	/**
	 * 分类名称
	 */
	private String typeName;
	/**
	 * 二级分类id
	 */
	private Integer typeId;
	/**
	 * 状态
	 */
	private Integer status;

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
	 * 是否促销
	 */
	private Integer promote;
	/**
	 * 当前页码
	 */
	private Integer pageNo=1;
	/**
	 * 每页显示数量
	 */
	private Integer pageSize = 10;

}
