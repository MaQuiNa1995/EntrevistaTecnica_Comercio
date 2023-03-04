package inditex.christian.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import inditex.christian.dto.ProductBaseDto;
import inditex.christian.dto.ProductOutputDto;
import inditex.christian.exception.EntityNotFoundException;
import inditex.christian.service.ProductService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/product")
public class ProductController {

	private final ProductService productService;

	/**
	 * http://localhost:port/product/?actualDate=XXXXXX&productId=YYYYY&brandId=ZZZ
	 * 
	 * @throws EntityNotFoundException When no result is found
	 *                                 <p>
	 *                                 Will be captured by
	 *                                 {@link inditex.christian.controller.handler.ControllerExceptionHandler#resourceNotFoundException(EntityNotFoundException)}
	 */
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public ProductOutputDto get(ProductBaseDto productBaseDto) throws EntityNotFoundException {

		return productService.find(productBaseDto.getProductId(), productBaseDto.getBrandId(),
				productBaseDto.getActualDate());
	}

}
