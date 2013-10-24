package com.fidelis.k2.enums;

public enum NotificationType {

	StudentAddedTeacher("Added Teacher"), StudentRemovedTeacher("Removed Teacher"), TeacherAddedStudent("Added Student"), TeacherRemovedStudent("Removed Student");

	NotificationType(String text){
		this.notificationText = text;
	}
	
	private String notificationText;
	public String getNotificationText() {
		return notificationText;
	}
	public void setNotificationText(String notificationText) {
		this.notificationText = notificationText;
	}
	

}
