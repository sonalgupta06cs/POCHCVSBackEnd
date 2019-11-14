/**
 * 
 */
package com.poc.hcvs.ws.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.poc.hcvs.ws.model.CustomerEntity;

/**
 * @author 143703
 *
 */
@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {

}
