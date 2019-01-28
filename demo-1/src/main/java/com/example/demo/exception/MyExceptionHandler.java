package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.entity.BaimoResult;
import com.example.demo.entity.BaimoResultEnum;

@RestControllerAdvice
public class MyExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public BaimoResult UserException(Exception e) {
		if (e instanceof UserException) {
			UserException UserException = (UserException) e;
			return new BaimoResult(UserException.getCode(), UserException.getFlag(), UserException.getData());
		} else {
			return new BaimoResult(BaimoResultEnum.UNKNOW_ERROR);
		}
	}
}
