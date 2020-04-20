package com.concrete.desafio.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.concrete.desafio.controller.dto.UsuarioDTO;
import com.concrete.desafio.controller.form.LoginForm;
import com.concrete.desafio.exception.ExceptionEmailNaoExiste;
import com.concrete.desafio.exception.ExceptionUsuarioSenhaInvalidos;
import com.concrete.desafio.service.UsuarioAutenticacaoService;

@RestController
public class UsuarioAutenticacaoController {
	
	@Autowired
	private UsuarioAutenticacaoService usuarioAutenticarService;
	
	@PostMapping("/login")
	public ResponseEntity<UsuarioDTO> autenticar(@RequestBody @Valid LoginForm form, UriComponentsBuilder uriBuilder) throws ExceptionEmailNaoExiste, ExceptionUsuarioSenhaInvalidos {
		UsuarioDTO usuarioDto = usuarioAutenticarService.autenticar(form);

		URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuarioDto.getId()).toUri();
		return ResponseEntity.created(uri).body(usuarioDto);
	}

}
