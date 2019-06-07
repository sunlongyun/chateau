package com.caichao.chateau.app.controller.response;

import com.caichao.chateau.app.controller.response.enums.ResponseCodeEnum;
import java.io.Serializable;
import lombok.Data;

/**
 * 返回值
 *
 * @author 孙龙云
 */
@Data
public class CCResponse implements Serializable {

  private static final long serialVersionUID = -7096601129585145021L;
  private String code = null;

  private String msg = null;
  private Object data = null;

  private CCResponse(String code, String msg, Object data) {
    this.code = code;
    this.msg = msg;
    this.data = data;
  }


  public static CCResponse success() {
    return success(ResponseCodeEnum.SUCCESS.msg());
  }

  public static CCResponse success(String msg) {
    CCResponse res = new CCResponse(ResponseCodeEnum.SUCCESS.code(), msg, null);
    return res;
  }


  public static CCResponse success(Object data) {
    CCResponse res = new CCResponse(ResponseCodeEnum.SUCCESS.code(), ResponseCodeEnum.SUCCESS.msg(), data);
    return res;
  }


  public static CCResponse fail(String msg) {
    CCResponse res = new CCResponse(ResponseCodeEnum.FAIL.code(), msg, null);
    return res;
  }



  public CCResponse() {

  }
}
