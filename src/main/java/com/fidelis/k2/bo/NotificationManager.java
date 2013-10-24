package com.fidelis.k2.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fidelis.k2.dao.NotificationDao;
import com.fidelis.k2.dao.StudentDao;
import com.fidelis.k2.dao.TeacherDao;
import com.fidelis.k2.entity.Notification;
import com.fidelis.k2.entity.User;
import com.fidelis.k2.enums.NotificationType;
import com.fidelis.k2.web.controller.HomeController;

@Service
public class NotificationManager {
private static final Logger logger = Logger.getLogger(NotificationManager.class);
@Autowired
private TeacherDao teacherDao;
@Autowired
private StudentDao studentDao;
@Autowired
private NotificationDao notificationDao;

@Transactional
public Notification savenotification(User createdBy,User createdFor,NotificationType type){
	Notification notification=new Notification(type, createdBy, createdFor, new Date());
	notificationDao.save(notification);
	logger.info("Saved Notifcation With Id "+notification.getId()+" for id "+ createdFor.getId());
	return notification;
	
}
@Transactional
public List<String> createnotificationtext(User user){
	List<String> notifications=new ArrayList<String>();
	for(Notification notification: user.getNotificationsForUser()){
		notifications.add(notification.getCreatedBy().getName()+ " "+notification.getNotificationType().getNotificationText()+" "+notification.getCreatedFor().getName()+" at "+notification.getDateCreated() );
	}
	logger.info("Returning Notifcations for user with id "+user.getId());
	return notifications;
}

}
