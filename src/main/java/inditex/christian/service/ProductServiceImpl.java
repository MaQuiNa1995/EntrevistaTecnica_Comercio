package inditex.christian.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Comparator;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import inditex.christian.domain.Product;
import inditex.christian.dto.ProductOutputDto;
import inditex.christian.exception.EntityNotFoundException;
import inditex.christian.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	@Override
	@Transactional(readOnly = true)
	public ProductOutputDto find(Long productId, Integer brandId, LocalDateTime actualDate)
			throws EntityNotFoundException {

		Product product = productRepository
				.findByProductIdAndBrandIdAndActualDate(productId, brandId, Timestamp.valueOf(actualDate))
				.max(Comparator.comparing(e -> e))
				.orElseThrow(EntityNotFoundException::new);

		return new ProductOutputDto(product, actualDate);
	}

}
