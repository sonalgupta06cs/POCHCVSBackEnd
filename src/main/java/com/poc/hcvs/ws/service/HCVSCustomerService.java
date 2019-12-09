package com.poc.hcvs.ws.service;

import java.util.List;

import com.poc.hcvs.ws.model.CustomerEntity;
import com.poc.hcvs.ws.shared.dto.CustomerDto;

public interface HCVSCustomerService {
	
	CustomerDto createCustomer(CustomerDto userDto);

	int deleteById(long[] id);

	List<CustomerEntity> findAll();

	CustomerDto updateCustomer(CustomerDto customer);

	List<CustomerEntity> findByCustomerNameLike(String searchText);

	List<CustomerEntity> findByTier1Like(String searchText);

	List<CustomerEntity> findByTier2Like(String searchText);

	List<CustomerDto> getAllCustomersName(String keyword);
	
	CustomerDto findById(Long id);
	
	/* List<CustomerDto> softDeleteByIds(Long[] ids); */

}
