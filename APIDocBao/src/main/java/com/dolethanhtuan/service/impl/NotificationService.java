package com.dolethanhtuan.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dolethanhtuan.dto.NotificationDTO;
import com.dolethanhtuan.entity.NewsEntity;
import com.dolethanhtuan.entity.NotificationEntity;
import com.dolethanhtuan.entity.NotificationId;
import com.dolethanhtuan.entity.UserEntity;
import com.dolethanhtuan.repository.NotificationRepository;
import com.dolethanhtuan.service.INotificationService;

@Component
public class NotificationService implements INotificationService{
	@Autowired
	private NotificationRepository notiRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public NotificationDTO updateStatus(int user_id, int news_id, int status) {
		NotificationEntity notiE = notiRepository.findById(new NotificationId(new UserEntity(user_id), new NewsEntity(news_id))).get();
		notiE.setStatus(status);
		notiE = notiRepository.save(notiE);
		return modelMapper.map(notiE, NotificationDTO.class);
	}
}
