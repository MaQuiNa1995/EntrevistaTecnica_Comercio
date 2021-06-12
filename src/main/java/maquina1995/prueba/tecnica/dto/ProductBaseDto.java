package maquina1995.prueba.tecnica.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductBaseDto implements Serializable {

	protected String actualDate;
	protected Long productId;
	protected Integer brandId;

}