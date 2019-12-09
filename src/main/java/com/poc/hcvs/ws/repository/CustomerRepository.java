/**
 * 
 */
package com.poc.hcvs.ws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.poc.hcvs.ws.model.CustomerEntity;
import com.poc.hcvs.ws.model.CustomersNameResult;

/**
 * @author 143703
 *
 */
@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {

	List<CustomerEntity> findBycustomerNameIgnoreCaseContaining(String searchText);

	List<CustomerEntity> findBytier1NameIgnoreCaseContaining(String searchText);

	List<CustomerEntity> findBytier2NameIgnoreCaseContaining(String searchText);

	CustomerEntity findCustomerByCustomerName(String customerName);
	
	@Transactional
	@Modifying
	@Query("Update Customers set active=:active where customerName=:customerName")
	void updateIsActive(@Param("customerName") String customerName, boolean active);

	@Query("SELECT c FROM Customers c WHERE c.active=:active")
	Iterable<CustomerEntity> findAllActiveUsers(boolean active);

	@Query("SELECT NEW com.poc.hcvs.ws.model.CustomersNameResult(c.id, c.customerName) FROM Customers c WHERE customerName LIKE %:keyword% AND active=false")
	public List<CustomersNameResult> getAllCustomersNames(@Param("keyword") String keyword);

	@Transactional
	@Modifying
	@Query("Update Customers set active=:active where id IN (:list)")
	int updateIsActiveToFalse(List<Long> list, boolean active);



}
