package com.chisong.green.farm.app.service.impl;

import com.chisong.green.farm.app.entity.Message;
import com.chisong.green.farm.app.dto.MessageDto;
import com.chisong.green.farm.app.mapper.MessageMapper;
import com.chisong.green.farm.app.service.MessageService;

import com.lianshang.generator.commons.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客服中心消息 服务实现类
 * </p>
 *
 * @author 孙龙云
 * @since 2020-03-01
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper,Message, MessageDto> implements MessageService {

}