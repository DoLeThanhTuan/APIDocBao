package com.dolethanhtuan.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NotificationDTO {
	private NewsDTO news;
	private int status;
	private Date notificateDate;
}
