package com.algaworks.algafood.api.exceptionhandler;

import org.springframework.validation.BindingResult;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ValidacaoException extends RuntimeException{

	private static final long serialVersionUID = 5635333418361062907L;

	private BindingResult bindingResult;
	
}
