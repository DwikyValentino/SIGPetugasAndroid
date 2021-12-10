package com.dwikyv.sigpertanian.Model.register;

import com.google.gson.annotations.SerializedName;

public class Register{

	@SerializedName("registerData")
	private RegisterData registerData;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public RegisterData getRegisterData(){
		return registerData;
	}

	public String getMessage(){
		return message;
	}

	public boolean isStatus(){
		return status;
	}
}