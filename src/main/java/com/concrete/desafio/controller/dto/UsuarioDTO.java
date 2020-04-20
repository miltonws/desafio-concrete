package com.concrete.desafio.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.concrete.desafio.modelo.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UsuarioDTO {

	private Long id;
	private String nome;
	private String email;
	private String password;
	@JsonProperty(value = "phones")
	private List<TelefoneDTO> telefones;
	@JsonProperty(value = "created")
	private LocalDateTime criado;
	@JsonProperty(value = "modified")
	private LocalDateTime modificado;
	@JsonProperty(value = "last_login")
	private LocalDateTime ultimo_login;
	private String token;

	public UsuarioDTO() {

	}

	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.password = usuario.getPassword();
		this.criado = usuario.getCriado();
		this.modificado = usuario.getModificado();
		this.ultimo_login = usuario.getUltimo_login();
		this.telefones = new TelefoneDTO().converterTelefoneParaDTO(usuario.getTelefone());
		this.token = usuario.getToken();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<TelefoneDTO> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<TelefoneDTO> telefones) {
		this.telefones = telefones;
	}

	public LocalDateTime getCriado() {
		return criado;
	}

	public void setCriado(LocalDateTime criado) {
		this.criado = criado;
	}

	public LocalDateTime getModificado() {
		return modificado;
	}

	public void setModificado(LocalDateTime modificado) {
		this.modificado = modificado;
	}

	public LocalDateTime getUltimo_login() {
		return ultimo_login;
	}

	public void setUltimo_login(LocalDateTime ultimo_login) {
		this.ultimo_login = ultimo_login;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<UsuarioDTO> converterUsuarioParaDTO(List<Usuario> usuario) {
		List<UsuarioDTO> usuarioDto = usuario.stream().map(user -> {
			UsuarioDTO obj = new UsuarioDTO();
			obj.setId(user.getId());
			obj.setNome(user.getNome());
			obj.setEmail(user.getEmail());
			obj.setPassword(user.getPassword());
			obj.setTelefones(new TelefoneDTO().converterTelefoneParaDTO(user.getTelefone()));
			obj.setCriado(user.getCriado());
			obj.setModificado(user.getModificado());
			obj.setUltimo_login(user.getUltimo_login());
			obj.setToken(user.getToken());
			return obj;
		}).collect(Collectors.toList());
		return usuarioDto;
	}

}
