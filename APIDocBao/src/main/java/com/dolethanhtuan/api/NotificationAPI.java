package com.dolethanhtuan.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dolethanhtuan.dto.NotificationDTO;
import com.dolethanhtuan.service.INotificationService;

@RestController
public class NotificationAPI {
	@Autowired
	private INotificationService notificationService;
	
	@PutMapping(path={"api/v1/notification/status"})
	public NotificationDTO updateStatus(@RequestParam("user_id") Integer user_id,
										@RequestParam("news_id") Integer news_id,
										@RequestParam("status") Integer status) {
		if(user_id == null || news_id == null)
			return null;
		return notificationService.updateStatus(user_id, news_id, status);
	}
}
