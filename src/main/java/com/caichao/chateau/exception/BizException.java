package com.caichao.chateau.exception;

import lombok.Data;

/**
 * 描述:
 *
 * @AUTHOR 孙龙云
 * @date 2019-06-09 16:24
 */
@Data
public class BizException extends RuntimeException {
	private String code;
	private String msg;

	public BizException(){

	}
	public BizException(String code, String msg){
		this.code = code;
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "BizException{" +
			"code='" + code + '\'' +
			", msg='" + msg + '\'' +
			'}';
	}
}
