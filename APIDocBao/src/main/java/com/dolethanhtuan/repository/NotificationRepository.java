package com.dolethanhtuan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dolethanhtuan.entity.NotificationEntity;
import com.dolethanhtuan.entity.NotificationId;

public interface NotificationRepository extends JpaRepository<NotificationEntity, NotificationId>{
}
