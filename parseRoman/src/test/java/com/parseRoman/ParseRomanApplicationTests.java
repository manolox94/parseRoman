package com.parseRoman;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import com.parseRoman.controllers.RomanController;
import com.parseRoman.services.ParseService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RunWith(SpringRunner.class)
@WebMvcTest(RomanController.class)
class ParseRomanApplicationTests {
	
	
	@Autowired
	private MockMvc mvc;
	
    @MockBean
    private ParseService parseService;

	@Test
	void contextLoads() throws Exception {
		
//		RequestBuilder request = MockMvcRequestBuilders.get("/test/parseRom");
//		MvcResult result = mvc.perform(request).andReturn();
//		System.out.println("PRUEBAS1");
		
//		Numeros numeros = new Numeros();
//		numeros.setNumeroRom("3");
		
//		String bodyReq = parseJSON(numeros);
		
		Mockito.when(parseService.numberToRoman(3)).thenReturn("III");
//		Mockito.verify(parseService).numberToRoman(3);
		
//		doThrow(IllegalArgumentException.class).when(parseService).numberToRoman(3);
//		
		mvc.perform(post("/test/parsePost")
                 .contentType(MediaType.APPLICATION_JSON)
                 .content("{\"numeroRom\": \"3\"}"))
         .andDo(print())
         .andExpect(status().isOk())
         .andExpect(content().string("III"));
		
		verify(parseService, times(1)).numberToRoman(3);

//		assertEquals("I",result.getResponse().getContentAsString());
	}
	
	@Test
	void contextLoadsError() throws Exception {
		
		Mockito.when(parseService.numberToRoman(0)).thenReturn("error");
		
		doThrow(IllegalArgumentException.class).when(parseService).numberToRoman(0);
		
		mvc.perform(post("/test/parsePost")
                 .contentType(MediaType.APPLICATION_JSON)
                 .content("{\"numeroRom\": \"0\"}"))
         .andDo(print())
         .andExpect(status().isBadRequest())
         .andExpect(content().string(""));
		
		verify(parseService, times(1)).numberToRoman(0);
		
	}

		

}
