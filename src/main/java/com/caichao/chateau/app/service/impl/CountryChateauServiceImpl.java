package com.caichao.chateau.app.service.impl;

import com.caichao.chateau.app.constants.enums.Validity;
import com.caichao.chateau.app.dto.CountryChateauDto;
import com.caichao.chateau.app.entity.CountryChateau;
import com.caichao.chateau.app.example.CountryChateauExample;
import com.caichao.chateau.app.mapper.CountryChateauMapper;
import com.caichao.chateau.app.service.CountryChateauService;
import com.caichao.chateau.app.utils.BroadCastUtil;
import com.lianshang.generator.commons.ServiceImpl;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * <p>
 * 国家--酒庄 服务实现类
 * </p>
 *
 * @author 孙龙云
 * @since 2019-06-15
 */
@Service
public class CountryChateauServiceImpl extends
	ServiceImpl<CountryChateauMapper, CountryChateau, CountryChateauDto> implements CountryChateauService {

	/**
	 * 推流地址前缀
	 */
	@Value("${pusher_prefix}")
	private String pusherPrefix;
	/**
	 * 播放地址缓存
	 */
	private Map<String, String[]> pusherMap = new HashMap<>();
	/**
	 * bizid
	 */
	@Value("${bizid}")
	private String bizId;

	@Override
	public String getDailyPusherUlr(Integer id) {
		CountryChateauDto countryChateauDto = getById(id);
		String url = "";
		if(null != countryChateauDto) {
			String roomId = "daily_" + countryChateauDto.getCode();
			url = getUrl(roomId);
		}
		return url;
	}

	/**
	 * 获取url
	 * @param roomId
	 * @return
	 */
	private String getUrl(String roomId){
		String url = "";
		String[] arr = pusherMap.get(roomId);
		if(null != arr) {
			Integer endTime = Integer.valueOf(arr[0]);
			if(endTime < (new Date().getTime())) {
				return arr[1];
			}
		}

		url = BroadCastUtil.getPusherUrl(pusherPrefix, bizId, roomId);
		Integer endTime = LocalDateTime.now().plusHours(10).getNano();
		arr = new String[]{endTime + "", url};

		pusherMap.put(roomId, arr);

		return url;
	}
	@Override
	public String getMasterPusherUlr(Integer id) {
		CountryChateauDto countryChateauDto = getById(id);
		String url = "";
		if(null != countryChateauDto) {
			String roomId = "master_" + countryChateauDto.getCode();
			url = getUrl(roomId);
		}
		return url;
	}

	@Override
	public CountryChateauDto getByCode(String code) {
		CountryChateauExample countryChateauExample = new CountryChateauExample();
		countryChateauExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code()).andCodeEqualTo(code);

		List<CountryChateauDto> chateauDtoList = getList(countryChateauExample);
		if(!CollectionUtils.isEmpty(chateauDtoList)){
			return chateauDtoList.get(0);
		}
		return null;
	}
}