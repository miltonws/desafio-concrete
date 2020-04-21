package com.concrete.desafio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.concrete.desafio.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	List<Usuario> findByNome(String nomeUsuario);
	Usuario findByEmail(String email);
	Usuario findByToken(String token);

}
