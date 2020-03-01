package com.chisong.green.farm.app.miniProgram.msg;

import com.chisong.green.farm.app.miniProgram.annotaion.MiniFiled;
import lombok.Data;

/**
 * 描述:
 * 图片内容
 * @AUTHOR 孙龙云
 * @date 2020-02-29 17:30
 */
@Data
public class ImageMsg extends ParentMsg {

	/**
	 * 图片内容
	 */
	private ImageContent image;
	/**
	 * 图片内容
	 */
	@Data
	public static class ImageContent{
		@MiniFiled("media_id")
		private String mediaId;
	}
}
