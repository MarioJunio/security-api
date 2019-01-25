package br.com.security.service;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class AuthCode {

	public String getCode() {
		String code = "";
		
		code += new Random().nextInt(10);
		code += new Random().nextInt(10);
		code += new Random().nextInt(10);
		code += new Random().nextInt(10);
		code += new Random().nextInt(10);
		code += new Random().nextInt(10);
		
		return code;
	}
}
