package com.fidelis.k2.exceptions;

public class TeacherValidationException extends Exception {

	private String message = null;
	public TeacherValidationException() {
		super();
	}
    public TeacherValidationException(String message) {
		super(message);
		this.message = message;
	}
    public TeacherValidationException(Throwable cause) {
        super(cause);
    }
 
    @Override
    public String toString() {
        return message;
    }
 
    @Override
    public String getMessage() {
        return message;
    }
	
	 
	
	
}
