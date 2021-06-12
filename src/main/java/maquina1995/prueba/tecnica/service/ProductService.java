package maquina1995.prueba.tecnica.service;

import java.sql.Timestamp;
import java.util.Comparator;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import maquina1995.prueba.tecnica.domain.Product;
import maquina1995.prueba.tecnica.dto.ProductOutputDto;
import maquina1995.prueba.tecnica.exception.EntityNotFoundException;
import maquina1995.prueba.tecnica.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

	@Transactional(readOnly = true)
	public ProductOutputDto find(Long productId, Integer brandId, String actualDate) throws EntityNotFoundException {

		Product product = productRepository
		        .findByProductIdAndBrandIdAndActualDate(productId, brandId, Timestamp.valueOf(actualDate))
		        .max(Comparator.comparing(e -> e))
		        .orElseThrow(EntityNotFoundException::new);

		return new ProductOutputDto(product, actualDate);
	}

}
