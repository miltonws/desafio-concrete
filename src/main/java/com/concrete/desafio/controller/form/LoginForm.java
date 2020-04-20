package com.concrete.desafio.controller.form;

import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

public class LoginForm {

	@NotNull
	@NotEmpty
	private String email;
	@NotNull
	@NotEmpty
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
