package com.dolethanhtuan.apiv2;

import com.dolethanhtuan.dto.NotificationDTO;
import com.dolethanhtuan.response.ResponseSuccess;
import com.dolethanhtuan.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationAPIV2 {
	@Autowired
	private INotificationService notificationService;
	
	@PutMapping(path={"api/notification/status/v2"})
	public ResponseSuccess<NotificationDTO> updateStatus(@RequestParam("user_id") Integer user_id,
										@RequestParam("news_id") Integer news_id,
										@RequestParam("status") Integer status) {
		if(user_id == null || news_id == null)
			return new ResponseSuccess<>(HttpStatus.BAD_REQUEST.value(),"Updated fail");
		return new ResponseSuccess<>(HttpStatus.OK.value(),"Update success",notificationService.updateStatus(user_id, news_id, status));
	}
}
