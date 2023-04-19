package com.example.controller;

import com.example.SpringBootApplicationTest;
import com.example.entity.Stock;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StockControllerTest extends SpringBootApplicationTest {


	@Autowired
	MockMvc mockMvc;

	ObjectMapper objectMapper = new ObjectMapper();

	@Test
	void addStockTest() throws Exception {
		Stock stock = new Stock(0, "Apple", 100.0, 2);
		mockMvc.perform(post("/addStock")
						.content(objectMapper.writeValueAsString(stock))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void getStockDataTest() throws Exception {
		mockMvc.perform(post("/getStockData")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void buyStockTest() throws Exception {
		Stock stock = new Stock(0, "YOTA", 120.0, 12);
		mockMvc.perform(post("/buyStock")
						.content(objectMapper.writeValueAsString(stock))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}


	@Test
	void changeStockTest() throws Exception {
		Stock stock = new Stock(1, "Samsung", 100.0, 32);
		mockMvc.perform(post("/changeStock")
						.content(objectMapper.writeValueAsString(stock))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}
