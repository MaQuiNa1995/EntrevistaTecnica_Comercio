package maquina1995.prueba.tecnica.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductPk implements Serializable {

	private Long productId;
	private Integer priceList;

}
