/**
 * 
 */
package com.poc.hcvs.ws.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.hcvs.ws.model.CustomerEntity;
import com.poc.hcvs.ws.repository.CustomerRepository;
import com.poc.hcvs.ws.shared.dto.CustomerDto;

/**
 * @author 143703
 *
 */
@Service
public class HCVSServiceImpl implements HCVSService {
	
	@Autowired
	CustomerRepository customerRepository;

	public CustomerDto createCustomer(CustomerDto customer) {
		
		/*
		 * if (customerRepository.findById(customer.getId()) != null) throw new
		 * RuntimeException("Customer already exists");log
		 */
		
		ModelMapper mapper = new ModelMapper();
		CustomerEntity userEntity  = mapper.map(customer, CustomerEntity.class);
		
		CustomerEntity storedUserResponse = customerRepository.save(userEntity);

		//BeanUtils.copyProperties(storedUserResponse, userResponse);
		CustomerDto custResponse = mapper.map(storedUserResponse, CustomerDto.class);

		return custResponse;
	}

	@Override
	public CustomerDto deleteById(long id) {
		
		Optional<CustomerEntity> customerToBeDeleted = customerRepository.findById(id);
        if(customerToBeDeleted.isPresent()){
        	customerRepository.delete(customerToBeDeleted.get());
        }
        
        ModelMapper mapper = new ModelMapper();
        CustomerDto custResponse  = mapper.map(customerToBeDeleted.get(), CustomerDto.class);
        return custResponse;
		
	}

	@Override
	public List<CustomerEntity> findAll() {
		
		List<CustomerEntity> customers = new ArrayList<>();
		customerRepository.findAll().forEach(customers::add);
		return customers;
	}

	@Override
	public CustomerDto updateCustomer(CustomerDto customer) {
		
		Optional<CustomerEntity> customerData = customerRepository.findById(customer.getId());
		 
	    if (customerData.isPresent()) {
	    	CustomerEntity _customer = customerData.get();
	      _customer.setCustomerName(customer.getCustomerName());
	      _customer.setTier1Name(customer.getTier1Name());
	      _customer.setTier2Name(customer.getTier2Name());
	      _customer.setTier3Name(customer.getTier3Name());
	      _customer.setTier4Name(customer.getTier4Name());
	      _customer.setTier5Name(customer.getTier5Name());
	      _customer.setTier6Name(customer.getTier6Name());
	      _customer.setLogo(customer.getLogo());
	      CustomerEntity customerEntity =  customerRepository.save(_customer);
	      ModelMapper mapper = new ModelMapper();
	      CustomerDto custResponse  = mapper.map(customerEntity, CustomerDto.class);
	      return custResponse;
	    } else {
	      return null;
	    }
	}

}
