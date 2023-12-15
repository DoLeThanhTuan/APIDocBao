package com.dolethanhtuan.service;

import org.springframework.stereotype.Service;

import com.dolethanhtuan.dto.NotificationDTO;

@Service
public interface INotificationService {
	NotificationDTO updateStatus(int user_id,int news_id,int status);
}
