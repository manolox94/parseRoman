package com.parseRoman;


import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import com.parseRoman.services.ParseService;
import com.parseRoman.services.ParseServiceImpl;

import lombok.extern.log4j.Log4j2;


@RunWith(MockitoJUnitRunner.class)
@Log4j2
class ParseServiceImplTests {

	
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
	
	public ParseService parsServ;
	
    @BeforeEach
    public void init() {
    	parsServ = new ParseServiceImpl();
    }
    
	@Test
	void contextLoads() throws Exception {
		String numRom = "";
		numRom = parsServ.numberToRoman(3);
		assertEquals("III",numRom);

//		verify(parsServ, times(1)).numberToRoman(3);

	}
	
    @Test
    public void contextLoadsError() throws  IllegalArgumentException {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("El numero debe ser mayor a 0");
        parsServ.numberToRoman(0);
    }

}
