package com.dwikyv.sigpertanian.Model.register;

import com.google.gson.annotations.SerializedName;

public class RegisterData {

	@SerializedName("fullname")
	private String fullname;

	@SerializedName("username")
	private String username;

	public String getFullname(){
		return fullname;
	}

	public String getUsername(){
		return username;
	}
}