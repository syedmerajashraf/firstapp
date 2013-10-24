package com.fidelis.k2.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fidelis.k2.enums.NotificationType;

@Entity
public class Notification {
	public Notification(){
		
	}
    
	public Notification(NotificationType notificationType, User createdBy,User createdFor, Date dateCreated){
		this.notificationType = notificationType;
		this.createdBy = createdBy;
		this.createdFor = createdFor;
		this.dateCreated = dateCreated;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Enumerated(EnumType.STRING)
	private NotificationType notificationType;
	@ManyToOne
	@JoinColumn(name="created_by_user_id")
	private User createdBy;
	@ManyToOne
	@JoinColumn(name="created_for_user_id")
	private User createdFor;
	private Date dateCreated;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public NotificationType getNotificationType() {
		return notificationType;
	}
	public void setNotificationType(NotificationType notificationType) {
		this.notificationType = notificationType;
	}
	public User getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	public User getCreatedFor() {
		return createdFor;
	}
	public void setCreatedFor(User createdFor) {
		this.createdFor = createdFor;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
}
