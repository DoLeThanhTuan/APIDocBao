package com.dolethanhtuan.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="notification")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NotificationEntity {
	@EmbeddedId
	private NotificationId notificationId;
	
	@Column(name="status")
	private int status;
	
	@Column(name="notificateDate")
	private Date notificateDate;
}
