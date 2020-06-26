package com.chisong.green.farm.app.controller.auth;

import com.alibaba.fastjson.JSONObject;
import com.chisong.green.farm.app.controller.response.CCResponse;
import com.chisong.green.farm.app.dto.CustomerInfoDto;
import com.chisong.green.farm.app.dto.GoodsDto;
import com.chisong.green.farm.app.miniProgram.response.LoginResponse;
import com.chisong.green.farm.app.service.AuthBizService;
import com.chisong.green.farm.app.service.CustomerInfoService;
import com.chisong.green.farm.app.service.GoodsService;
import com.chisong.green.farm.app.utils.CurrentUserUtils;
import com.chisong.green.farm.app.utils.LoginUserInfoUtil;
import com.lianshang.utils.JsonUtils;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLEncoder;

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import java.awt.image.BufferedImage;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 描述:
 *
 * @AUTHOR 孙龙云
 * @date 2019-06-07 19:13
 */
@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

	public static final String TOKEN_URL = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=";
	public static final String NOT_GOODS_PAGE = "-1";
	@Autowired
	private AuthBizService authBizService;
	@Autowired
	private CustomerInfoService customerInfoService;

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private GoodsService goodsService;


	@Value("${image_path}")
	private String imageDir;
	/**
	 * 获取联系人信息
	 * @return
	 */
	@RequestMapping("/getContacts")
	public CCResponse getContacts(){
		Map<String, Object> contact = new HashMap<>();
		Map<String, Object> contact1 = new HashMap<>();

		contact.put("contactName","小沈");
		contact.put("mobile", "13402***253");

		contact1.put("contactName","小朱");
		contact1.put("mobile", "19821***781");

		List<Map<String, Object>> list  = new ArrayList<>();
		list.add(contact);
		list.add(contact1);

		Map<String, Object> contactMap = new HashMap<>();
		contactMap.put("contactList", list);

		return CCResponse.success(contactMap);
	}
	/**
	 * 登录接口
	 */
	@RequestMapping("/login")
	public CCResponse login(String code) {
		log.info("登录开始:{}",code);
		String userCode = authBizService.login(code);

		LoginResponse loginResponse = LoginUserInfoUtil.getLoginResponse(userCode);
		Map<String,Object> userMap = new HashMap<>();
		userMap.put("userCode",userCode);
		userMap.put("openId",loginResponse.getOpenid());

		log.info("userMap:{}", userMap);
		return CCResponse.success(userMap);
	}

	/**
	 * 获取accessToken
	 */
	@RequestMapping("/getAccessToken")
	public CCResponse getAccessToken() {

		Map<String, Object> objectMap = new HashMap<>();
		objectMap.put("accessToken", authBizService.getAccessToken());

		return CCResponse.success(objectMap);
	}

	/**
	 * 创建海报
	 */
	@RequestMapping("/createMap")
	public void createMap(HttpServletResponse httpServletResponse) throws IOException {

		String recommendId = null;
		try{
			LoginResponse loginResponse = CurrentUserUtils.get();
			CustomerInfoDto customerInfoDto = customerInfoService.getCustomerInfoDtoByOpenId(loginResponse.getOpenid());
			recommendId = customerInfoDto.getId()+"";
		}catch(Exception ex){
			log.info("获取当前用户失败: {}", ex);
		}

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
			.getRequest();

		if(StringUtils.isEmpty(recommendId)){
			recommendId = request.getParameter("recommendId");
		}
		if(StringUtils.isEmpty(recommendId)){
			recommendId="1";
		}

		String goodsId = request.getParameter("goodsId");
		if(StringUtils.isEmpty(goodsId)){
			goodsId = NOT_GOODS_PAGE;
		}


		//生成二维码，本地暂存
	    String qrPath =	saveImg(imageDir, goodsId, recommendId);

		GoodsDto goodsDto = goodsService.getById(Integer.parseInt(goodsId));

		String mapPath = drawCouponPosterImage(qrPath, goodsDto, recommendId);
		FileInputStream fileInputStream = new FileInputStream(new File(mapPath));

		IOUtils.copyLarge(fileInputStream, httpServletResponse.getOutputStream());

		fileInputStream.close();
	}

	/**
	 * 文件保存在临时目录
	 * @param dir
	 * @return
	 */
	private String saveImg(String dir, String goodsId,String recommendId) throws IOException {
		String fileName = goodsId+"_"+recommendId+".jpg";
		byte[] bytes = getQrcodeBytes( goodsId, recommendId);
		File imageDir = new File(dir);
		if(!imageDir.exists()){
			imageDir.mkdirs();
		}
		File file = new File(imageDir+fileName);
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		fileOutputStream.write(bytes);
		fileOutputStream.close();

		return  file.getAbsolutePath();
	}
	/**
	 * 获取二维码字节
	 * @return
	 */
	private byte[] getQrcodeBytes(String goodsId,String recommendId) throws UnsupportedEncodingException {

		Map<String, Object> dataMap  = (Map<String, Object>)getAccessToken().getData();
		String accessToken = (String) dataMap.get("accessToken");

		String scene = goodsId+"&"+recommendId;

		String page ="pages/shopDeatil/shopDeatil";
		if(NOT_GOODS_PAGE.equals(goodsId)){
			page="pages/index/index";
		}
		String url = TOKEN_URL+accessToken;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

		Map<String, Object> map = new HashMap<>();
		map.put("width", 430);
		map.put("page",page);
		map.put("scene",scene);
		map.put("auto_color", false);

		String content = JsonUtils.object2JsonString(map);
		content = content.replace("\\u0026", "&");
		HttpEntity<String> req = new HttpEntity<>(content, headers);
		ResponseEntity<byte[]> postForEntity =	restTemplate.exchange(url, HttpMethod.POST,req,byte[].class);
		return postForEntity.getBody();
	}


	/**
	 * 生成海报
	 * @param qrPath
	 * @param goodsDto
	 * @return
	 */
	private String drawCouponPosterImage(String qrPath, GoodsDto goodsDto, String recommendId) {
		try {
			BufferedImage goodsImage = ImageIO.read(new URL(goodsDto.getPicUrl()));

			// 透明底
			BufferedImage gbGroundImg = new BufferedImage(750, 1250, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics2d = gbGroundImg.createGraphics();
			graphics2d.setColor(Color.WHITE);
			graphics2d.fillRect(0, 0, 750, 1250);// 填充整个屏幕
			graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);                        // 消除画图锯齿
			graphics2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);    // 消除文字锯齿
			Integer y = 0;
			String userName ="未知";
			CustomerInfoDto customerInfoDto = customerInfoService.getById(recommendId);
			if(null != customerInfoDto){
				userName = customerInfoDto.getNickName();
			}
		    y=	writeTitle(graphics2d,userName, y);
			y = writeGoodsImage(goodsImage, graphics2d, y);
			y = writeGoodsInfo(goodsDto, graphics2d, y);

			// 写入二维码
			BufferedImage qrcodeImage =  ImageIO.read(new File(qrPath));
			Rectangle qrcodeRectangle = new Rectangle(50, y, 180, 180);
			graphics2d.drawImage(qrcodeImage.getScaledInstance(qrcodeRectangle.width, qrcodeRectangle.height,
				Image.SCALE_SMOOTH),
				qrcodeRectangle.x, qrcodeRectangle.y, null);

			// 长按识别二维码下单
			graphics2d.setFont(new Font("宋体", Font.PLAIN, 30));
			graphics2d.setColor(new Color(89, 89, 89));
			graphics2d.drawString("长按识别二维码下单", 280, y+90);


			graphics2d.dispose();
			File dir = new File(imageDir+"/share/"+recommendId+"/");
			if(!dir.exists()){
				dir.mkdirs();
			}
			String absolutePath = imageDir+"/share/"+recommendId+"/goods.jpg";
			File file =  new File(absolutePath);
			if(!file.exists()){
				file.createNewFile();
			}
			ImageIO.write(gbGroundImg, "jpg", file);
			return absolutePath;
		} catch(IOException e) {
			log.error("生成优惠券海报失败", e);
		}

		return "";
	}

	/**
	 * 写商品图片
	 * @param goodsImage
	 * @param graphics2d
	 * @param y
	 * @return
	 */
	private int writeGoodsImage(BufferedImage goodsImage, Graphics2D graphics2d, Integer y) {
		Rectangle rectangle = new Rectangle(45, y, 660, 660);
		graphics2d.drawImage(goodsImage.getScaledInstance(rectangle.width, rectangle.height,
			Image.SCALE_SMOOTH),
			rectangle.x, rectangle.y, null);
		y += 700;
		return y;
	}

	/**
	 * 写标题
	 * @param graphics2d
	 * @param y
	 */
	private int writeTitle(Graphics2D graphics2d,String userName, Integer y) {
		graphics2d.setFont(new Font("宋体", Font.PLAIN, 35));
		graphics2d.setColor(Color.red);
		graphics2d.drawString("叮当农场小程序", 200, y + 55);
	    y+=70;

		if(!"未知".equals(userName)){
			// 推荐人
			graphics2d.drawRect(46, y + 20, 95, 50);
			graphics2d.setColor(Color.gray);
			graphics2d.fillRect(46, y + 20, 95, 50);
			// 推荐人
			graphics2d.setFont(new Font("宋体", Font.BOLD, 25));
			graphics2d.setColor(Color.white);
			graphics2d.drawString("推荐人", 55, y +55);

			graphics2d.setFont(new Font("宋体", Font.PLAIN, 25));
			graphics2d.setColor(Color.gray);
			graphics2d.drawString(userName, 160, y+55);
			y+=66;
		}


		return  y;
	}

	/**
	 * 写商品信息
	 * @param goodsDto
	 * @param graphics2d
	 * @param y
	 */
	private int writeGoodsInfo(GoodsDto goodsDto, Graphics2D graphics2d, Integer y) {
		// 写入商品名
		graphics2d.setFont(new Font("宋体", Font.PLAIN, 32));
		graphics2d.setColor(Color.BLACK);
		String title = goodsDto.getTitle();
		char[] sc = title.toCharArray();
		int titleOffsetY = 0;
		String titleSub = "";
		int row = 0;
		int j = 0;
		for(int i = 0; i < title.length(); i++) {
			titleSub = title.substring(j, i);
			int titleSubW = graphics2d.getFontMetrics().charsWidth(sc, j, i - j);
			int len = row > 0 ? 450 : 400;
			if(titleSubW > len) {
				j = i;
				int ox = row > 0 ? 15 : 75;
				row++;
				if(row == 3 && j < title.length() - 1) {
					titleSub = titleSub.substring(0, titleSub.length() - 1) + "...";
				}
				graphics2d.drawString(titleSub, ox, y + titleOffsetY);

				if(row == 3) {
					break;
				} else {
					titleOffsetY += 40;
				}
			}
		}
		titleSub = title.substring(j, title.length());
		graphics2d.drawString(titleSub, 46, y + titleOffsetY);

		y = y + titleOffsetY + 40;

		graphics2d.setFont(new Font("宋体", Font.PLAIN, 25));
		graphics2d.setColor(Color.gray);
		graphics2d.drawString("商品价格以实际价格为准", 30, y+20);
		y += 30;

		// 到手价背景
		graphics2d.drawRect(25, y + 20, 90, 40);
		graphics2d.setColor(new Color(255, 0, 61));
		graphics2d.fillRect(25, y + 20, 90, 40);

		// 到手价
		graphics2d.setFont(new Font("宋体", Font.BOLD, 25));
		graphics2d.setColor(Color.white);
		graphics2d.drawString("到手价", 33, y + 53);

		// 到手价人民币图标
		graphics2d.setFont(new Font("宋体", Font.PLAIN, 36));
		graphics2d.setColor(Color.red);
		graphics2d.drawString("¥", 150, y + 60);

		// 到手价金额
		graphics2d.setFont(new Font("宋体", Font.BOLD, 60));
		graphics2d.setColor(Color.red);
		graphics2d.drawString(goodsDto.getPromotePrice()/100+"" , 170, y+60);

		// 原价
		graphics2d.setFont(new Font("宋体", Font.PLAIN, 30));
		graphics2d.setColor(new Color(89, 89, 89));
		graphics2d.drawString("原价: ", 350, y + 60);

		// 原价金额
		graphics2d.setFont(new Font("宋体", Font.PLAIN, 30));
		graphics2d.setColor(new Color(89, 89, 89));
		graphics2d.drawString("¥" + (goodsDto.getPrice()/100)+"" , 425, y+60);
		y += 120;
		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);                        // 消除画图锯齿
		graphics2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
			RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

//		y+=100;

		return y;
	}
}
