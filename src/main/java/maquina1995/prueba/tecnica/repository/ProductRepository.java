package maquina1995.prueba.tecnica.repository;

import java.sql.Timestamp;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import maquina1995.prueba.tecnica.domain.Product;
import maquina1995.prueba.tecnica.domain.ProductPk;

public interface ProductRepository extends JpaRepository<Product, ProductPk> {

	@Query(value = "SELECT pro FROM Product pro WHERE pro.productPk.productId=:productId AND "
	        + "pro.brandId=:brandId AND :actualDate BETWEEN pro.startDate AND pro.endDate")
	Stream<Product> findByProductIdAndBrandIdAndActualDate(Long productId, Integer brandId, Timestamp actualDate);

}
