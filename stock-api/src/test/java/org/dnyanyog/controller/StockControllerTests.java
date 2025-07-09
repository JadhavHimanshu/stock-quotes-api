package org.dnyanyog.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.dnyanyog.common.ResponseCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc

class StockControllerTests {
	  @Autowired  
	  private MockMvc mockMvc;

	  @Test
	  @WithMockUser(roles = "USER") 
	  public void getSingleStockQuote() throws Exception {
	    mockMvc
	        .perform(MockMvcRequestBuilders.get("/api/auth/quotes/AAPL")
	            .accept(MediaType.APPLICATION_JSON)
	            )
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.message").value(ResponseCode.FETCH_STOCK_SUCCESS.getMessage()));
	  }

	  @Test
	  @WithMockUser(roles = "USER") 
	  public void getBatchStockQuotes() throws Exception {
	    mockMvc
	        .perform(MockMvcRequestBuilders.get("/api/auth/quotes")
	            .param("symbols", "AAPL,GOOG,MSFT")
	            .accept(MediaType.APPLICATION_JSON)
	            )
	        .andExpect(status().isOk()) ;
	  }
	}

