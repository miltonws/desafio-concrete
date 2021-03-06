package com.concrete.desafio.service.impl;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concrete.desafio.controller.dto.UsuarioDTO;
import com.concrete.desafio.controller.form.UsuarioForm;
import com.concrete.desafio.exception.ExceptionEmailNaoExiste;
import com.concrete.desafio.exception.ExceptionIdInvalido;
import com.concrete.desafio.exception.ExceptionNaoAutorizado;
import com.concrete.desafio.exception.ExceptionSessaoInvalida;
import com.concrete.desafio.modelo.Telefone;
import com.concrete.desafio.modelo.Usuario;
import com.concrete.desafio.repository.TelefoneRepository;
import com.concrete.desafio.repository.UsuarioRepository;
import com.concrete.desafio.service.UsuarioService;

@Service
public class UsarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private TelefoneRepository telefoneRepository;

	@Autowired
	private TokenService tokenService;

	public UsuarioDTO registrarUsuario(UsuarioForm form) throws ExceptionEmailNaoExiste {
		Usuario usuario = new Usuario();
		Usuario existeUsuario = null;

		// verifica se ja existe email cadastrado
		existeUsuario = usuarioRepository.findByEmail(form.getEmail());
		if (existeUsuario != null) {
			throw new ExceptionEmailNaoExiste();
		}

		usuario.setNome(form.getNome());
		usuario.setEmail(form.getEmail());
		usuario.setPassword(form.getPassword());
		usuario.setCriado(LocalDateTime.now());
		usuario.setUltimo_login(LocalDateTime.now());
		usuario.setModificado(LocalDateTime.now());
		usuario.setToken(tokenService.geradorToken());

		List<Telefone> telefones = form.getTelefones().stream().map(tel -> {
			Telefone objTel = new Telefone();
			objTel.setDdd(tel.getDdd());
			objTel.setNumero(tel.getNumero());
			objTel.setUsuario(usuario);
			return objTel;
		}).collect(Collectors.toList());

		usuario.setTelefone(telefones);
		usuarioRepository.save(usuario);

		telefoneRepository.saveAll(usuario.getTelefone());

		return new UsuarioDTO(usuario);

	}

	@Override
	public UsuarioDTO detalharUsuario(Long id, String Authorization) throws ExceptionNaoAutorizado, ExceptionSessaoInvalida {
		Usuario usuarioToken = usuarioRepository.findByToken(Authorization);
		UsuarioDTO resposta = null;

		if (usuarioToken != null) {
			Optional<Usuario> usuarioId = usuarioRepository.findById(id);
			if (usuarioId.isPresent() && usuarioId.get().getToken().equals(usuarioToken.getToken())) {
				
				LocalDateTime horaAtualMenosTrinta = LocalDateTime.now().plusMinutes(-30);
				
				if(usuarioId.get().getUltimo_login().isBefore(horaAtualMenosTrinta)) {
					resposta = new UsuarioDTO(usuarioId.get());					
				}else {
					throw new ExceptionSessaoInvalida();
				}
				
			} else {
				throw new ExceptionNaoAutorizado();
			}
		} else {
			throw new ExceptionNaoAutorizado();
		}

		return resposta;
	}

	@Override
	public List<UsuarioDTO> listarUsuarios() {
		List<Usuario> usuarios;

		usuarios = usuarioRepository.findAll();

		return new UsuarioDTO().converterUsuarioParaDTO(usuarios);
	}

}
