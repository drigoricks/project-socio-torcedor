package br.com.programa.torcedor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Email já cadastrado!")
public class EmailException extends Exception{
	private static final long serialVersionUID = 7889455865L;
}