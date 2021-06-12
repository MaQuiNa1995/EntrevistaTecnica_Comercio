package functional.maquina1995.prueba.tecnica;

import java.net.URI;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import maquina1995.prueba.tecnica.Main;
import maquina1995.prueba.tecnica.dto.ProductBaseDto;
import maquina1995.prueba.tecnica.dto.ProductOutputDto;

@SpringBootTest(classes = Main.class,
        webEnvironment = WebEnvironment.RANDOM_PORT)
class ProductControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@LocalServerPort
	private Integer localPort;

	@Test
	void productFinalPrizeForDay14Hour10Test() {
		// given
		int day = 14;
		int hour = 10;
		ProductBaseDto productBaseDto = createProduct(day, hour);

		// when
		ResponseEntity<ProductOutputDto> response = this.callToController(productBaseDto);

		// then
		ProductOutputDto productDto = response.getBody();
		Assertions.assertAll(() -> Assertions.assertEquals(HttpStatus.OK, response.getStatusCode()),
		        () -> Assertions.assertEquals(35.5F, productDto.getFinalPrice()),
		        () -> Assertions.assertEquals("2020-06-" + day + " " + hour + ":00:00.000", productDto.getActualDate()),
		        () -> Assertions.assertEquals(1, productDto.getBrandId()),
		        () -> Assertions.assertEquals(1, productDto.getRateApplied()));
	}

	@Test
	void productFinalPrizeForDay14Hour16Test() {

		// given
		int day = 14;
		int hour = 16;
		ProductBaseDto productBaseDto = createProduct(day, hour);

		// when
		ResponseEntity<ProductOutputDto> response = this.callToController(productBaseDto);

		// then
		ProductOutputDto productDto = response.getBody();
		Assertions.assertAll(() -> Assertions.assertEquals(HttpStatus.OK, response.getStatusCode()),
		        () -> Assertions.assertEquals(25.45F, productDto.getFinalPrice()),
		        () -> Assertions.assertEquals("2020-06-" + day + " " + hour + ":00:00.000", productDto.getActualDate()),
		        () -> Assertions.assertEquals(1, productDto.getBrandId()),
		        () -> Assertions.assertEquals(2, productDto.getRateApplied()));
	}

	@Test
	void productFinalPrizeForDay14Hour21Test() {

		// given
		int day = 14;
		int hour = 21;
		ProductBaseDto productBaseDto = this.createProduct(day, hour);

		// when
		ResponseEntity<ProductOutputDto> response = this.callToController(productBaseDto);

		// then
		ProductOutputDto productDto = response.getBody();
		Assertions.assertAll(() -> Assertions.assertEquals(HttpStatus.OK, response.getStatusCode()),
		        () -> Assertions.assertEquals(35.5F, productDto.getFinalPrice()),
		        () -> Assertions.assertEquals("2020-06-" + day + " " + hour + ":00:00.000", productDto.getActualDate()),
		        () -> Assertions.assertEquals(1, productDto.getBrandId()),
		        () -> Assertions.assertEquals(1, productDto.getRateApplied()));
	}

	@Test
	void productFinalPrizeForDay15Hour10Test() {

		// given
		int day = 15;
		int hour = 10;
		ProductBaseDto productBaseDto = this.createProduct(day, hour);

		// when
		ResponseEntity<ProductOutputDto> response = this.callToController(productBaseDto);

		// then
		ProductOutputDto productDto = response.getBody();
		Assertions.assertAll(() -> Assertions.assertEquals(HttpStatus.OK, response.getStatusCode()),
		        () -> Assertions.assertEquals(30.5F, productDto.getFinalPrice()),
		        () -> Assertions.assertEquals("2020-06-" + day + " " + hour + ":00:00.000", productDto.getActualDate()),
		        () -> Assertions.assertEquals(1, productDto.getBrandId()),
		        () -> Assertions.assertEquals(3, productDto.getRateApplied()));
	}

	@Test
	void productFinalPrizeForDay16Hour21Test() {

		// given
		int day = 16;
		int hour = 21;
		ProductBaseDto productBaseDto = this.createProduct(day, hour);

		// when
		ResponseEntity<ProductOutputDto> response = this.callToController(productBaseDto);

		// then
		ProductOutputDto productDto = response.getBody();
		Assertions.assertAll(() -> Assertions.assertEquals(HttpStatus.OK, response.getStatusCode()),
		        () -> Assertions.assertEquals(38.95F, productDto.getFinalPrice()),
		        () -> Assertions.assertEquals("2020-06-" + day + " " + hour + ":00:00.000", productDto.getActualDate()),
		        () -> Assertions.assertEquals(1, productDto.getBrandId()),
		        () -> Assertions.assertEquals(4, productDto.getRateApplied()));
	}

	@Test
	void productBrandIdNotFoundTest() {

		// given
		ProductBaseDto productBaseDto = this.createProduct(16, 21);
		productBaseDto.setBrandId(2);
		// when
		ResponseEntity<ProductOutputDto> response = this.callToController(productBaseDto);

		// then
		Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

	@Test
	void productProductIdNotFoundTest() {

		// given
		ProductBaseDto productBaseDto = this.createProduct(16, 21);
		productBaseDto.setProductId(2L);

		// when
		ResponseEntity<ProductOutputDto> response = this.callToController(productBaseDto);

		// then
		Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

	@Test
	void productProductIdAndBrandIdNotFoundTest() {

		// given
		ProductBaseDto productBaseDto = this.createProduct(16, 21);
		productBaseDto.setProductId(2L);
		productBaseDto.setBrandId(9999);

		// when
		ResponseEntity<ProductOutputDto> response = this.callToController(productBaseDto);

		// then
		Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

	private ResponseEntity<ProductOutputDto> callToController(ProductBaseDto productBaseDto) {
		UriComponentsBuilder paramBuilder = this.queryParamBuilder(productBaseDto.getActualDate(),
		        productBaseDto.getProductId(), productBaseDto.getBrandId());

		ResponseEntity<ProductOutputDto> response = this.doGet(paramBuilder);
		return response;
	}

	private UriComponentsBuilder queryParamBuilder(String actualDate, Long productId, Integer brandId) {
		return UriComponentsBuilder.fromHttpUrl("http://localhost:" + localPort + "/product")
		        .queryParam("actualDate", actualDate)
		        .queryParam("productId", productId)
		        .queryParam("brandId", brandId);
	}

	private ResponseEntity<ProductOutputDto> doGet(UriComponentsBuilder builder) {
		URI uri = builder.build()
		        .encode()
		        .toUri();
		return testRestTemplate.exchange(uri, HttpMethod.GET, null, ProductOutputDto.class);
	}

	private ProductBaseDto createProduct(int day, int hour) {
		ProductBaseDto productBaseDto = new ProductBaseDto();
		productBaseDto.setActualDate("2020-06-" + day + " " + hour + ":00:00.000");
		productBaseDto.setBrandId(1);
		productBaseDto.setProductId(35455L);
		return productBaseDto;
	}

}
