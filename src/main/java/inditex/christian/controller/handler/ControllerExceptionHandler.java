package inditex.christian.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import inditex.christian.constant.SwaggerConstants;
import inditex.christian.exception.EntityNotFoundException;

@RestControllerAdvice(basePackages = SwaggerConstants.PROJECT_CONTROLLER_PATH)
public class ControllerExceptionHandler {

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ExceptionHandler(value = EntityNotFoundException.class)
	public String resourceNotFoundException(EntityNotFoundException exception) {
		return exception.getMessage();
	}

}
