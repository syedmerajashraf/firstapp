package com.fidelis.k2.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "user")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="discriminator",
    discriminatorType=DiscriminatorType.STRING
)
public class User {
	
	public User(){}
	public User(String name, String email) {
		this.name = name;
		this.email = email;
	}
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
	private int id;
	@NotEmpty
    @NotNull
	private String name;
	@Email
	@NotEmpty
    @NotNull
	private String email;
	@OneToMany(mappedBy="createdFor")
	private List<Notification> notificationsForUser;
	@OneToMany(mappedBy="createdBy")
	private List<Notification> notificationsByUser;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Notification> getNotificationsForUser() {
		return notificationsForUser;
	}
	public void setNotificationsForUser(List<Notification> notificationsForUser) {
		this.notificationsForUser = notificationsForUser;
	}
	public List<Notification> getNotificationsByUser() {
		return notificationsByUser;
	}
	public void setNotificationsByUser(List<Notification> notificationsByUser) {
		this.notificationsByUser = notificationsByUser;
	}
	

	

}
