package maquina1995.prueba.tecnica.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import maquina1995.prueba.tecnica.domain.Product;
import maquina1995.prueba.tecnica.domain.ProductPk;

@Getter
@Setter
@NoArgsConstructor
public class ProductOutputDto extends ProductBaseDto implements Serializable {

	private Integer rateApplied;
	private Float finalPrice;

	public ProductOutputDto(Product product, String actualDate) {
		super();
		super.actualDate = actualDate;
		super.brandId = product.getBrandId();

		ProductPk pk = product.getProductPk();
		super.productId = pk.getProductId();
		this.rateApplied = pk.getPriceList();

		this.finalPrice = product.getPrice();
	}

}
