package com.ifbp.pkg.jsonObjects;

public class ResponseObject {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "CreateHomeItemResponseObject [message=" + message + "]";
	}

	public ResponseObject() {

	}

}
