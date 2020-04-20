package com.concrete.desafio.service;

import com.concrete.desafio.controller.dto.UsuarioDTO;
import com.concrete.desafio.controller.form.LoginForm;
import com.concrete.desafio.exception.ExceptionUsuarioSenhaInvalidos;

public interface UsuarioAutenticacaoService {
	
	public UsuarioDTO autenticar(LoginForm form) throws ExceptionUsuarioSenhaInvalidos;

}
