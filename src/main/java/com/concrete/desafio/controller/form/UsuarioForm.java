package com.concrete.desafio.controller.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.concrete.desafio.modelo.Telefone;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

public class UsuarioForm {

	@NotNull
	@NotEmpty
	@JsonProperty(value = "name")
	private String nome;
	@NotNull
	@NotEmpty
	private String email;
	@NotNull
	@NotEmpty
	private String password;
	@JsonProperty(value = "phones")
	private List<Telefone> telefones;

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

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

}
