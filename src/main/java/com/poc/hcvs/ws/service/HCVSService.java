package com.poc.hcvs.ws.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.poc.hcvs.ws.model.CustomerEntity;
import com.poc.hcvs.ws.shared.dto.CustomerDto;

public interface HCVSService {
	
	CustomerDto createCustomer(CustomerDto userDto);

	CustomerDto deleteById(long id);

	List<CustomerEntity> findAll();

	CustomerDto updateCustomer(CustomerDto customer);

}
