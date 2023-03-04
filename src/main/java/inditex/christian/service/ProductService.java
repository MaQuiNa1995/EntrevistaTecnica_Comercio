package inditex.christian.service;

import java.time.LocalDateTime;

import inditex.christian.dto.ProductOutputDto;
import inditex.christian.exception.EntityNotFoundException;

public interface ProductService {

	ProductOutputDto find(Long productId, Integer brandId, LocalDateTime actualDate) throws EntityNotFoundException;

}