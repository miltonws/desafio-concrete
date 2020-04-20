package com.concrete.desafio.service;

import java.util.List;

import com.concrete.desafio.controller.dto.UsuarioDTO;
import com.concrete.desafio.controller.form.UsuarioForm;
import com.concrete.desafio.exception.ExceptionEmailNaoExiste;

public interface UsuarioService {

	public UsuarioDTO registrarUsuario(UsuarioForm form) throws ExceptionEmailNaoExiste;
	public UsuarioDTO detalharUsuario(Long id);
	List<UsuarioDTO> listarUsuarios();
}
