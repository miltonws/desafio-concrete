package com.concrete.desafio.config.validacao;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.concrete.desafio.exception.ExceptionEmailNaoExiste;
import com.concrete.desafio.exception.ExceptionUsuarioSenhaInvalidos;

@RestControllerAdvice
public class ErroValidacaoHandler {
	
	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroFormularioDTO> handle(MethodArgumentNotValidException exception) {
		List<ErroFormularioDTO> dto = new ArrayList<>();
		
		List<FieldError> fieldErros = exception.getBindingResult().getFieldErrors();
		fieldErros.forEach( e -> {
			String mensagem = messageSource.getMessage(e,  LocaleContextHolder.getLocale());
			ErroFormularioDTO erro = new ErroFormularioDTO(e.getField(), mensagem);
			dto.add(erro);
		});
		return dto;
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ExceptionEmailNaoExiste.class)
	public ErroDTO handle(ExceptionEmailNaoExiste exception) {
		ErroDTO dto = new ErroDTO();
		
		String mensagem = "E-mail já existente";
		dto.setMensagem(mensagem);

		return dto;
	}
	
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(ExceptionUsuarioSenhaInvalidos.class)
	public ErroDTO handle(ExceptionUsuarioSenhaInvalidos exception) {
		ErroDTO dto = new ErroDTO();
		
		String mensagem = "Usuário e/ou senha inválidos";
		dto.setMensagem(mensagem);

		return dto;
	}
}
