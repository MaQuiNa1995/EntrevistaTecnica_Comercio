package maquina1995.prueba.tecnica.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import maquina1995.prueba.tecnica.constant.SwaggerConstants;
import maquina1995.prueba.tecnica.exception.EntityNotFoundException;
import maquina1995.prueba.tecnica.exception.ErrorMessage;

@RestControllerAdvice(basePackages = SwaggerConstants.PROJECT_CONTROLLER_PATH)
public class ControllerExceptionHandler {

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ExceptionHandler(value = EntityNotFoundException.class)
	public ErrorMessage resourceNotFoundException(EntityNotFoundException exception, WebRequest request) {
		return ErrorMessage.builder()
		        .message(exception.getMessage())
		        .statusCode(HttpStatus.OK.value())
		        .build();
	}

}
