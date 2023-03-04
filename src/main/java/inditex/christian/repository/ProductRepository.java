package inditex.christian.repository;

import java.sql.Timestamp;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import inditex.christian.domain.Product;
import inditex.christian.domain.ProductPk;

public interface ProductRepository extends JpaRepository<Product, ProductPk> {

	@Query(value = "SELECT pro FROM Product pro WHERE pro.productPk.productId=:productId AND "
	        + "pro.brandId=:brandId AND :actualDate BETWEEN pro.startDate AND pro.endDate")
	Stream<Product> findByProductIdAndBrandIdAndActualDate(Long productId, Integer brandId, Timestamp actualDate);

}
