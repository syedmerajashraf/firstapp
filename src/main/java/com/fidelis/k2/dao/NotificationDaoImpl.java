package com.fidelis.k2.dao;

import org.springframework.stereotype.Repository;

import com.fidelis.k2.entity.Notification;

@Repository
public class NotificationDaoImpl extends BaseDaoImpl<Notification> implements NotificationDao {

	public NotificationDaoImpl() {
		super(Notification.class);
	}
}
