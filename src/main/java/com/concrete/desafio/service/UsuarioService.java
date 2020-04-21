package com.concrete.desafio.service;

import java.util.List;

import com.concrete.desafio.controller.dto.UsuarioDTO;
import com.concrete.desafio.controller.form.UsuarioForm;
import com.concrete.desafio.exception.ExceptionEmailNaoExiste;
import com.concrete.desafio.exception.ExceptionNaoAutorizado;
import com.concrete.desafio.exception.ExceptionSessaoInvalida;

public interface UsuarioService {

	public UsuarioDTO registrarUsuario(UsuarioForm form) throws ExceptionEmailNaoExiste;
	public UsuarioDTO detalharUsuario(Long id, String Authorization) throws ExceptionNaoAutorizado, ExceptionSessaoInvalida;
	List<UsuarioDTO> listarUsuarios();
}
