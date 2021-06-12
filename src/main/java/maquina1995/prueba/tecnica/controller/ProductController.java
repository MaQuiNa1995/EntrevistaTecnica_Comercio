package maquina1995.prueba.tecnica.controller;

import javax.websocket.server.PathParam;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import maquina1995.prueba.tecnica.dto.ProductOutputDto;
import maquina1995.prueba.tecnica.exception.EntityNotFoundException;
import maquina1995.prueba.tecnica.service.ProductService;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/product")
public class ProductController {

	private final ProductService productService;

	/**
	 * @throws EntityNotFoundException this will be captured by
	 *                                 {@link maquina1995.prueba.tecnica.controller.handler.ControllerExceptionHandler}
	 */
	@GetMapping
	public ResponseEntity<ProductOutputDto> find(@PathParam(value = "actualDate") String actualDate,
	        @PathParam(value = "productId") Long productId, @PathParam(value = "brandId") Integer brandId)
	        throws EntityNotFoundException {
		return ResponseEntity.ok(productService.find(productId, brandId, actualDate));
	}

}
