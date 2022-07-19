package com.parseRoman.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParseServiceImpl implements ParseService{
	
	 @Autowired
	public ParseServiceImpl() {
	}

	@Override
	public String numberToRoman(int num) {
		if(num == 0) {
			throw new IllegalArgumentException(String.format("El numero debe ser mayor a 0"));
		}
		
//		System.out.println("Integer: " + num);  
		int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};  
		String[] romanLetters = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};  
		StringBuilder roman = new StringBuilder();  
		for(int i=0;i<values.length;i++)   
		{  
			while(num >= values[i])   
			{  
				num = num - values[i];  
				roman.append(romanLetters[i]);  
			}  
		}  
//		System.out.println("Corresponding Roman Numerals is: " + roman.toString());  
		
		return roman.toString();
	}

}
