package com.concrete.desafio.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.concrete.desafio.controller.dto.UsuarioDTO;
import com.concrete.desafio.controller.form.UsuarioForm;
import com.concrete.desafio.exception.ExceptionEmailNaoExiste;
import com.concrete.desafio.exception.ExceptionNaoAutorizado;
import com.concrete.desafio.exception.ExceptionSessaoInvalida;
import com.concrete.desafio.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public List<UsuarioDTO> listar() {
		return usuarioService.listarUsuarios();
	}

	@PostMapping
	public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody @Valid UsuarioForm form, UriComponentsBuilder uriBuilder) throws ExceptionEmailNaoExiste {
		UsuarioDTO usuarioDto = usuarioService.registrarUsuario(form);

		URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuarioDto.getId()).toUri();
		return ResponseEntity.created(uri).body(usuarioDto);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> detalhar(@PathVariable Long id, @RequestHeader String Authorization) throws ExceptionNaoAutorizado, ExceptionSessaoInvalida {
		UsuarioDTO detalharUsuario = usuarioService.detalharUsuario(id, Authorization);

		if (detalharUsuario == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(detalharUsuario);

	}
}
