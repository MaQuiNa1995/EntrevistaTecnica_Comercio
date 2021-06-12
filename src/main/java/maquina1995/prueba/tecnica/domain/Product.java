package maquina1995.prueba.tecnica.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

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
	private Float price;
	@Enumerated(EnumType.STRING)
	@Column(length = 3)
	private Currency curr;

	/**
	 * Used by
	 * {@link maquina1995.prueba.tecnica.service.ProductService#find(Long, Integer, String)}
	 */
	@Override
	public int compareTo(Product otherProduct) {
		return this.priority.compareTo(otherProduct.getPriority());
	}

}
