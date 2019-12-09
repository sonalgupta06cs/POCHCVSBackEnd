package com.poc.hcvs.ws.service;

import org.springframework.util.StringUtils;


public class Test {

	    public static void main(String args[]) {

	        Long[] arr = {1L, 2L, 3L};
	        String models = StringUtils.arrayToCommaDelimitedString(arr);

	        System.out.println("string: " + models);

	    }

}
