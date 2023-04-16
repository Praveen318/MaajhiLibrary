package com.example.demo.globalExpceptionHandler;

@SuppressWarnings("serial")
public class CustomException extends RuntimeException {
	public CustomException(String message) {
		super(message);
	}
}
