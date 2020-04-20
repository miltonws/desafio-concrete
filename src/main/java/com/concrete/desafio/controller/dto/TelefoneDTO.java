package com.concrete.desafio.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.concrete.desafio.modelo.Telefone;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TelefoneDTO {

	private String ddd;

	@JsonProperty(value = "number")
	private String numero;

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public List<TelefoneDTO> converterTelefoneParaDTO(List<Telefone> telefone) {
		List<TelefoneDTO> telefoneDto = telefone.stream().map(tel -> {
			TelefoneDTO objTelefone = new TelefoneDTO();
			objTelefone.setDdd(tel.getDdd());
			objTelefone.setNumero(tel.getNumero());
			return objTelefone;
		}).collect(Collectors.toList());
		return telefoneDto;
	}

}
