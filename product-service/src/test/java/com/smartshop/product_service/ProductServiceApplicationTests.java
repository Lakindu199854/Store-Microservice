package com.smartshop.product_service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.assertions.Assertions;
import com.smartshop.product_service.dto.CreateProductRequestDTO;
import com.smartshop.product_service.dto.ProductResponseDTO;
import com.smartshop.product_service.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {
	/*
When we start the intergration test first it start a mongoDB container and then will add the replicatesturl
to the spring.data.mongodb.uri property in the application.properties file dynamically when starting the test
Here we are'nt using the local mongoDB database.We use a mongoDB docker container.
*/

	@Autowired
	ProductRepository productRepository;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Container
	static MongoDBContainer mongoDBContainer=new MongoDBContainer("mongo:4.0.6");

	@DynamicPropertySource
	static void properties(DynamicPropertyRegistry dynamicPropertyRegistry) {
		dynamicPropertyRegistry.add("spring.data.mongodb.uri",mongoDBContainer::getReplicaSetUrl);
	}

	//Here we check if the response has CREATED status
	@Test
	void shouldCreateProduct() throws Exception {
		CreateProductRequestDTO productRequest=getProductRequest();
		//Here we convert the object to JSON format
		String productRequestJson =objectMapper.writeValueAsString(productRequest);
		mockMvc.perform(MockMvcRequestBuilders.post("api/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(productRequestJson))
				.andExpect(status().isCreated());
		Assertions.assertTrue(productRepository.findAll().size()==1);
	}

	private CreateProductRequestDTO getProductRequest(){
		return CreateProductRequestDTO.builder()
				.name("iphone 13")
				.name("iphone 13")
				.price(BigDecimal.valueOf(1000))
				.build();
	}
}
