package com.concrete.desafio.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concrete.desafio.controller.dto.UsuarioDTO;
import com.concrete.desafio.controller.form.LoginForm;
import com.concrete.desafio.exception.ExceptionEmailNaoExiste;
import com.concrete.desafio.exception.ExceptionUsuarioSenhaInvalidos;
import com.concrete.desafio.modelo.Usuario;
import com.concrete.desafio.repository.UsuarioRepository;
import com.concrete.desafio.service.UsuarioAutenticacaoService;

@Service
public class UsuarioAutenticacaoServiceImpl implements UsuarioAutenticacaoService{

	@Autowired
	private UsuarioRepository usuarioRepository;

	
	@Override
	public UsuarioDTO autenticar(LoginForm form) throws ExceptionUsuarioSenhaInvalidos {
		Usuario usuario = new Usuario();
		UsuarioDTO usuarioDto = new UsuarioDTO();
		
		//verifica se ja existe email cadastrado
		usuario = usuarioRepository.findByEmail(form.getEmail());
		if(usuario != null) {
			if(usuario.getPassword().equals(form.getPassword())) {
				usuario.setUltimo_login(LocalDateTime.now());
				usuarioRepository.save(usuario);
				
				usuarioDto = new UsuarioDTO(usuario);
			}else {
				throw new ExceptionUsuarioSenhaInvalidos();				
			}	
		}else {
			throw new ExceptionUsuarioSenhaInvalidos();			
		}

		return usuarioDto;
	}

	
}
