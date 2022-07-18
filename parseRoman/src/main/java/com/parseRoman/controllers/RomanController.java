package com.parseRoman.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parseRoman.Numeros;
import com.parseRoman.services.ParseService;

@RestController
@RequestMapping("test")

public class RomanController {
	
	private final ParseService parseService;
	
	
	@Autowired
	public RomanController(final ParseService parseService) {
		this.parseService = parseService;
	}


	@GetMapping("/parseRom")
	public Numeros parseRom(@RequestParam(value = "numero", defaultValue = "1") int numero) {
		
		String test = "";
		
		try {
			test = parseService.numberToRoman(numero);
			
		} catch (IllegalArgumentException e) {
			test = e.getMessage().toString();
		}
		
		return new Numeros(test);
	}
	
	
	@PostMapping("/parsePost")
	public ResponseEntity parseRom2(@RequestBody Numeros num) {

		String test = "1";

		try {
			System.out.println("TESTERO " + num.getNumeroRom());
			test = parseService.numberToRoman(Integer.valueOf(num.getNumeroRom()));
			System.out.println("TESTERO2 " + test);
			return new ResponseEntity(test, HttpStatus.OK);			
		} catch (IllegalArgumentException e) {
			System.out.println("TESTERO3");

			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	
//	@GetMapping("/parseRom2")
//	public ResponseEntity parseRom2(@RequestParam(value = "numero", defaultValue = "1") int numero) {
//		
//		String test = "1";
//		
//		try {
//			test = parseService.numberToRoman(numero);
//			return new ResponseEntity("test", HttpStatus.OK);			
//		} catch (IllegalArgumentException e) {
//			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
//		}
//		
//	}

	
	

}
