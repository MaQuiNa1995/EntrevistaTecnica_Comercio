package inditex.christian.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import inditex.christian.domain.Product;
import inditex.christian.domain.ProductPk;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductOutputDto extends ProductBaseDto implements Serializable {

	private Integer rateApplied;
	private Double finalPrice;
	private String currency;

	public ProductOutputDto(Product product, LocalDateTime actualDate) {
		super();
		super.actualDate = actualDate;
		super.brandId = product.getBrandId();

		ProductPk pk = product.getProductPk();
		super.productId = pk.getProductId();
		this.rateApplied = pk.getPriceList();

		this.currency = product.getCurr()
				.toString();
		this.finalPrice = product.getPrice();
	}

}
