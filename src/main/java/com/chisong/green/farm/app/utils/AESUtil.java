package com.chisong.green.farm.app.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import lombok.extern.slf4j.Slf4j;

/**
 * 描述:
 *
 * @AUTHOR 孙龙云
 * @date 2020-05-04 15:50
 */
@Slf4j
public class AESUtil {
	/**
	 * 密钥算法
	 */
	private static final String ALGORITHM = "AES";
	/**
	 * 加解密算法/工作模式/填充方式
	 */
	private static final String ALGORITHM_MODE_PADDING = "AES/ECB/PKCS5Padding";

	/**
	 * AES加密
	 *
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String encryptData(String data, String keyStr) throws Exception {
		/**
		 * 生成key
		 */
		SecretKeySpec key =
			new SecretKeySpec(MD5.MD5Encode(keyStr).toLowerCase().getBytes(), ALGORITHM);
		// 创建密码器
		Cipher cipher = Cipher.getInstance(ALGORITHM_MODE_PADDING);
		// 初始化
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return Base64Util.encode(cipher.doFinal(data.getBytes()));
	}

	/**
	 * AES解密
	 *
	 * @param base64Data
	 * @return
	 * @throws Exception
	 */
	public static String decryptData(String base64Data, String keyStr) throws Exception {
		/**
		 * 生成key
		 */
	SecretKeySpec key =
			new SecretKeySpec(MD5.MD5Encode(keyStr).toLowerCase().getBytes(), ALGORITHM);

		Cipher cipher = Cipher.getInstance(ALGORITHM_MODE_PADDING);
		cipher.init(Cipher.DECRYPT_MODE, key);
		return new String(cipher.doFinal(Base64Util.decode(base64Data)));
	}

	public static void main(String[] args) throws Exception {
		//解密
//        String req_info="Ih5osM/5IbPfHouVrUmwebd1yAW2Gys91jv006W1237sSi3z022KxHafLIDMrQLYiBttTadgvy2cbx6DnmwDIQ52lPWfo6pAAHt7Q9DjBIpDRQ7JsbEBlomoQP2ZkdNHnWscVYuFEVlItaSlkSlcKLdB4UwMduqDYseFsUUthz6htPeBu987zXS6dKrgIbRwOxt5RfPmk1sf0oVB2yU3UH0Ly8SzBjmN1jrh4qAaUkfH6VkeMJcsZSGchQn2VresxJTbGH++JE1UsXUF3gyYpweyxBPtHoKdaggsIONR20UKNxJYPJLnEOnfQF/Ipmk8/QmTVRK7iqfVLC9EA1Auma0AlKBjZlYqynUlF3y+E2ZzgWMUlvDHZVWDbzp/TcE0q+Ukc7yQ3HBsibDR474SPlLTkCWz1iydXzkVcLqJKamsh76Liv1a0hzu0sI3qasMAfmwU6/q7/N6quq031toO1GxqkVaxBRK7e64gSOx9ArxxVFgZ7WN+JPq2OH/pTKH8ToxHA0rtxN5+aAgZGkXiIOUiHtp4mjpRxqe34WK7C7Nr0DQyOVwsXT2TTegSgWGm34aa//ZYxHedubv2iX+E7K222lptg9IqHlMXBbwKFtKtIcal61+8ciz+sB1FBpqHchC+3whTqWv5ZANiHBzaOhbIbA/mKX2XZ6Cy0iYh+bL/8Y/Hvz/UnMGzor+2anIUeBAGRQmseL4jY+Qic46WLuEhDcarCaO4JgJSAOC+VmsdrER9TRum26PFwTQwtNpxkrKCiO9Gv36Ood5D8hXnLHUH+4nbsek8ouxkCcFXq4Us0mipB3i5ksQpt23LiJm9Ahxyvptp9Q41SytS48NXiz3IxTOqDdknowedZwAtJ/fhBlwiOHD9N+pECXuNBKLaCZcatGycr0/DPELiCF+MIRQ6V60wzaZD74TKRFULd1ljNsoQIAbuGaT40WMDY6a28jBHQ/IXnD4gvSvfeumwQzp3Q9PiPyFtF6JxH7RBRj9/lmQuQozJIPZCaCNVTBfWQOdcFaBnPLN0ZNvzjA93g6jcIxHzkXHmiGfh98vq2E=";
		String req_info = "DKRdlf1YyaeiF0uxAwq3VbqmklON8X3q/LFXL7gqgcnU6SSPOSj/x/v7YXW0zUQqRGXq6rIvySj5ietqoTbYxRhOstkFXHgqQiMJOl97YCIE2TEq8TaTn6u6Mo15ZlOKtpEqit9ZaulGqX6PWrXequc5OWFMB5yko1sPuzQ2iCiAS7kMXTvb/iI6DP1Yh3BiJYOlqujIeZPyzJnqcrfzIuq/Rr7K5h87XLrIfhrklPbL4BZ8Xlcj4HqIfnxftaCEAlR8xjQrwhGJEG9vI4ib/MRneGvJ8Nie7tCYspto29wRX28oZttuWVk2RRjCiU9umu5jagKF8tOMs0BI7gjwCt0RIulAR/uaidfdcAHel0R4W1NJrYRVhPzL5qpjuMvvzG5QHQ2QbMhdZ2CU121WBcNYYWta8XwG7TKTaI8Kz/aXuiZQLzc4pYAf4SWaRao/neMz6+le52WkmSH77zdtfM07hC9j+kZxGa51RVGIPk9F6742pjszqSRSS7k4wD3+QXsmO1EeKX+lyeBH8y1oHQSi74kuAeSvUg6bNCXIvdgXrAN+l35RXF+Pt8+3YHSnOxRbV4ua69erjdWR2gDUQvIY/0c10j8Dny7kLsIL/3MjV5o9ZAMEqaT1IT3oMVIN6z5xedbQfasK5pH0AnOZLL3mF0WAoi0klbwSUggJS3zbfbyCjKiLuDZH/hVeASYX94Z0yku018l4bqpsnGuuhuw5IWMwZXOy51plfKn9a2fLG9bEP5Tmqb4gKOKpoaqFwIuvW11lu+CKoP/80hyA+NjArjdN1DKdsVJbsBHsIb7HpTiXtaDlurkCjbonB9yk+bWT+qGi7v+82coX8axYVkDJ1Bz5PB2obGYkwji8vLU5T9UysTLrZj7zCIomL7au1y/mZlC5VzaFAKnmNZJ9llLJHwmDLNYqMoidolCe4BStABYKV/YN1Nrfi6piVQ+cMnV585Dqc0sXwMwgjGgJc9jYfZSwTEZNe/41x7ZYaYYYC8CqxNY8rhbk7OoDt7YAPJJhqG5ANvYMs4RY31lWoGHqWG8wGIqWbMZsvGlh638ToilmLsw5klWvta+2b5TT";
		String B = AESUtil.decryptData(req_info,"sunlongyun1478529630sunlongyun12");
		System.out.println(B);

	}
}
