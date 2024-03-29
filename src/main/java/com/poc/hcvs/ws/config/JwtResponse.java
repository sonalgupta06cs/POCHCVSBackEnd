package com.poc.hcvs.ws.config;
import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	
	private final String email;
	private final String jwttoken;

	public JwtResponse(String jwttoken, String email) {
		this.jwttoken = jwttoken;
		this.email = email;
	}

	public String getToken() {
		return this.jwttoken;
	}

	public String getEmail() {
		return email;
	}
}