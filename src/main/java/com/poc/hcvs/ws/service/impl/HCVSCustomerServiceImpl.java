/**
 * 
 */
package com.poc.hcvs.ws.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.hcvs.ws.exceptions.ErrorMessages;
import com.poc.hcvs.ws.exceptions.HCVSCustomerNotFoundException;
import com.poc.hcvs.ws.model.CustomerEntity;
import com.poc.hcvs.ws.model.CustomersNameResult;
import com.poc.hcvs.ws.model.UserEntity;
import com.poc.hcvs.ws.repository.CustomerRepository;
import com.poc.hcvs.ws.repository.UserRepository;
import com.poc.hcvs.ws.service.HCVSCustomerService;
import com.poc.hcvs.ws.shared.dto.CustomerDto;

/**
 * @author 143703
 *
 */
@Service
public class HCVSCustomerServiceImpl implements HCVSCustomerService {

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired 
	UserRepository userReporitory;
	
	@PersistenceContext
    private EntityManager em;

	public CustomerDto createCustomer(CustomerDto customer) {

		ModelMapper mapper = new ModelMapper();
		CustomerEntity customerEntity = mapper.map(customer, CustomerEntity.class);
		CustomerDto custResponse = new CustomerDto();

		if (customerRepository.findCustomerByCustomerName(customer.getCustomerName()) != null) {

			customerRepository.updateIsActive(customer.getCustomerName(), true);

			customerEntity = customerRepository.findCustomerByCustomerName(customer.getCustomerName());
			custResponse = mapper.map(customerEntity, CustomerDto.class);

			return custResponse;

			// throw new RuntimeException("Customer already exists");

		} else {
            
			//UserEntity user = em.getReference(UserEntity.class, userReporitory.findIdByFullName(customer.getMappedUser()));
			//customerEntity.setUserDetails(user);
		    Optional<UserEntity> userEntities = userReporitory.findById(customer.getUserId());
		    if(userEntities.isPresent()) {
		    	UserEntity userEntity = userEntities.get();
		    	customerEntity.setUserDetails(userEntity);
		    	customerEntity.setActive(true);
		    }
		    CustomerEntity storedUserResponse = customerRepository.save(customerEntity);

			if(storedUserResponse != null) {
				custResponse = mapper.map(storedUserResponse, CustomerDto.class);
				custResponse.setUserId(storedUserResponse.getUserDetails().getId());
			}
			

			return custResponse;
		}
	}

	@Override
	public int deleteById(long[] arr) {

		/*
		 * Optional<CustomerEntity> customerToBeDeleted = null;
		 * 
		 * for (long customerId : id) { customerToBeDeleted =
		 * customerRepository.findById(customerId); if (customerToBeDeleted.isPresent())
		 * {
		 * 
		 * customerRepository.updateIsActive(customerToBeDeleted.get().getCustomerName()
		 * , false); // customerRepository.delete(customerToBeDeleted.get()); } } id =
		 * null; ModelMapper mapper = new ModelMapper(); CustomerDto custResponse =
		 * mapper.map(customerToBeDeleted.get(), CustomerDto.class); return
		 * custResponse;
		 */
          // List<Long> ar =  Arrays.asList(arr);
		  List<Long> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
	 	  // String idsSeparated = StringUtils.arrayToCommaDelimitedString(id);
		  
		  int updatedRows = customerRepository.updateIsActiveToFalse(list, false);
		  
		  if(updatedRows != 0)
		      arr = null; 
		  // ModelMapper mapper = new ModelMapper();
		  // CustomerDto custResponse =  mapper.map(customerToBeDeleted.get(), CustomerDto.class); return
		  return  updatedRows;
		 

	}

	@Override
	public List<CustomerEntity> findAll() {

		List<CustomerEntity> customers = new ArrayList<>();
		customerRepository.findAllActiveUsers(true).forEach(customers::add);
		if(customers != null || !customers.isEmpty()) {
			return customers;
		} else {
			throw new HCVSCustomerNotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		}
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
			if(customer.getLogo() != null) {
				_customer.setLogo(customer.getLogo());	
			}
			_customer.setActive(customer.isActive());
			
			if(_customer.isActive()) {
			   int updateNoFlag = _customer.getUpdateNoFlag();
			   _customer.setUpdateNoFlag(++updateNoFlag);
			} else {
				_customer.setUpdateNoFlag(_customer.getUpdateNoFlag());
			}
			
			CustomerEntity customerEntity = customerRepository.save(_customer);
			ModelMapper mapper = new ModelMapper();
			CustomerDto custResponse = mapper.map(customerEntity, CustomerDto.class);
			return custResponse;
		} else {
			throw new HCVSCustomerNotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		}
	}

	@Override
	public List<CustomerEntity> findByCustomerNameLike(String searchText) {

		List<CustomerEntity> foundustomers = customerRepository.findBycustomerNameIgnoreCaseContaining(searchText);
		return foundustomers;
	}

	@Override
	public List<CustomerEntity> findByTier1Like(String searchText) {

		List<CustomerEntity> foundustomers = customerRepository.findBytier1NameIgnoreCaseContaining(searchText);
		return foundustomers;
	}

	@Override
	public List<CustomerEntity> findByTier2Like(String searchText) {

		List<CustomerEntity> foundustomers = customerRepository.findBytier2NameIgnoreCaseContaining(searchText);
		return foundustomers;
	}

	@Override
	public List<CustomerDto> getAllCustomersName(String keyword) {
		List<CustomerDto> customerDtoList = new ArrayList<CustomerDto>();
		List<CustomersNameResult> customerNames = customerRepository.getAllCustomersNames(keyword);
		for (CustomersNameResult c : customerNames) {
			CustomerDto customerDto = new CustomerDto();
			customerDto.setId(c.getId());
			customerDto.setCustomerName(c.getCustomerName());
			customerDtoList.add(customerDto);
		}
		return customerDtoList;

	}

	@Override
	public CustomerDto findById(Long id) {
		return customerRepository.findById(id).map(enitity -> new ModelMapper().map(enitity, CustomerDto.class)).get();
	}

	/*
	 * @Override public List<CustomerDto> softDeleteByIds(Long[] ids) {
	 * 
	 * CriteriaBuilder cb = em.getCriteriaBuilder(); //Using criteria builder you
	 * can build your criteria queries. Criteria crit =
	 * em.unwrap(Session.class).createCriteria(CustomerEntity.class);
	 * crit.add(Restrictions.in("id", ids)); List<CustomerDto> customers =
	 * crit.list(); return customers; }
	 */

}
