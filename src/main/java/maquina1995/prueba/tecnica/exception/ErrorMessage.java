package maquina1995.prueba.tecnica.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorMessage {
	private Integer statusCode;
	private String message;
}
