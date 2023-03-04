package inditex.christian;

import java.net.URI;
import java.time.LocalDateTime;

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

import inditex.christian.dto.ProductBaseDto;
import inditex.christian.dto.ProductOutputDto;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
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
				() -> Assertions.assertEquals(this.createDate(day, hour), productDto.getActualDate()),
				() -> Assertions.assertEquals(1, productDto.getBrandId()),
				() -> Assertions.assertEquals(1, productDto.getRateApplied()),
				() -> Assertions.assertEquals("EUR", productDto.getCurrency()));
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
				() -> Assertions.assertEquals(25.45D, productDto.getFinalPrice()),
				() -> Assertions.assertEquals(this.createDate(day, hour), productDto.getActualDate()),
				() -> Assertions.assertEquals(1, productDto.getBrandId()),
				() -> Assertions.assertEquals(2, productDto.getRateApplied()),
				() -> Assertions.assertEquals("EUR", productDto.getCurrency()));
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
				() -> Assertions.assertEquals(this.createDate(day, hour), productDto.getActualDate()),
				() -> Assertions.assertEquals(1, productDto.getBrandId()),
				() -> Assertions.assertEquals(1, productDto.getRateApplied()),
				() -> Assertions.assertEquals("EUR", productDto.getCurrency()));
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
				() -> Assertions.assertEquals(this.createDate(day, hour), productDto.getActualDate()),
				() -> Assertions.assertEquals(1, productDto.getBrandId()),
				() -> Assertions.assertEquals(3, productDto.getRateApplied()),
				() -> Assertions.assertEquals("EUR", productDto.getCurrency()));
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
				() -> Assertions.assertEquals(38.95D, productDto.getFinalPrice()),
				() -> Assertions.assertEquals(this.createDate(day, hour), productDto.getActualDate()),
				() -> Assertions.assertEquals(1, productDto.getBrandId()),
				() -> Assertions.assertEquals(4, productDto.getRateApplied()),
				() -> Assertions.assertEquals("EUR", productDto.getCurrency()));
	}

	private ResponseEntity<ProductOutputDto> callToController(ProductBaseDto productBaseDto) {
		UriComponentsBuilder paramBuilder = this.queryParamBuilder(productBaseDto.getActualDate(),
				productBaseDto.getProductId(), productBaseDto.getBrandId());

		ResponseEntity<ProductOutputDto> response = this.doGet(paramBuilder);
		return response;
	}

	private UriComponentsBuilder queryParamBuilder(LocalDateTime actualDate, Long productId, Integer brandId) {
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
		productBaseDto.setActualDate(this.createDate(day, hour));
		productBaseDto.setBrandId(1);
		productBaseDto.setProductId(35455L);
		return productBaseDto;
	}

	private LocalDateTime createDate(int day, int hour) {
		return LocalDateTime.of(2020, 6, day, hour, 0, 0);
	}

}
