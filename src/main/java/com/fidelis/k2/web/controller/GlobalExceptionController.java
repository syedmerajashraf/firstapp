package com.fidelis.k2.web.controller;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.fidelis.k2.exceptions.CustomGenericException;


@ControllerAdvice
public class GlobalExceptionController {
	private static final Logger logger = Logger.getLogger(GlobalExceptionController.class);
	@ExceptionHandler(CustomGenericException.class)
	public ModelAndView handleCustomException(CustomGenericException ex) {
		logger.info("A Custom Exception Has Been Thrown");
		logger.error( "Error Message:"+ex.getErrMsg());
		ModelAndView model = new ModelAndView("error");
		model.addObject("errCode", ex.getErrCode());
		model.addObject("errMsg", ex.getErrMsg());
		return model;
	}
 
	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {
		logger.info("An Exception Has Been Thrown");
		StringWriter stack = new StringWriter();
		ex.printStackTrace(new PrintWriter(stack));
		logger.error( "Error Message:"+ex.getMessage()+" \tError Reason:"+stack.toString()+"\tError Class: "+ex.getClass());
		ModelAndView model = new ModelAndView("error");
		model.addObject("errMsg", "Something Is Wrong AS The Request Cant Be Completed");
		return model;
	}

}
