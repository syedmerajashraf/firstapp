/*package com.fidelis.k2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ExceptionHandler {
	@ExceptionHandler(TeacherValidationException.class)
    ResponseEntity<String> test() {
		System.out.println("exception handler called");
        return new ResponseEntity<String>(
                "We are sorry, The Teacher Canot be saved.",
                HttpStatus.I_AM_A_TEAPOT  );
    }

}*/
