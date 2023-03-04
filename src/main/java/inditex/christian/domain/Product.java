package inditex.christian.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Currency;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Product implements Serializable, Comparable<Product> {

	@EmbeddedId
	private ProductPk productPk;
	private Integer brandId;
	private Integer priority;
	private Timestamp startDate;
	private Timestamp endDate;
	private Double price;
	private Currency curr;

	/**
	 * Used by
	 * {@link inditex.christian.service.ProductServiceImpl#find(Long, Integer, String)}
	 */
	@Override
	public int compareTo(Product otherProduct) {
		return this.priority.compareTo(otherProduct.getPriority());
	}

}
