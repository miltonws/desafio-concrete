package com.concrete.desafio.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.concrete.desafio.modelo.Usuario;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	private static final long tempoExpiracao = 1800000;
	private String key = "Secret";
	
	public String geradorToken() {
		return Jwts.builder()
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setSubject("usuario")
				.setExpiration(new Date(System.currentTimeMillis() + tempoExpiracao))
				.signWith(SignatureAlgorithm.HS256, key)
				.compact();
	}
}
