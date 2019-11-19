/**
 * 
 */
package com.poc.hcvs.ws.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.poc.hcvs.ws.model.CustomerEntity;

/**
 * @author 143703
 *
 */
@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {

	List<CustomerEntity> findBycustomerNameIgnoreCaseContaining(String searchText);

	List<CustomerEntity> findBytier1NameIgnoreCaseContaining(String searchText);

	List<CustomerEntity> findBytier2NameIgnoreCaseContaining(String searchText);

}
