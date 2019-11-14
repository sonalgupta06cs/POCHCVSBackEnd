/**
 * 
 */
package com.poc.hcvs.ws.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.activation.FileTypeMap;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.poc.hcvs.ui.entity.request.CustomerDetailsRequestModel;
import com.poc.hcvs.ui.entity.response.CustomerResponse;
import com.poc.hcvs.ws.model.CustomerEntity;
import com.poc.hcvs.ws.service.HCVSService;
import com.poc.hcvs.ws.shared.dto.CustomerDto;

/**
 * @author 143703
 *
 */
@CrossOrigin(origins = "http://localhost:4300")
@RestController
@RequestMapping(value = "/api")
public class HCVSController {

	@Autowired
	HCVSService hcvsService;
	
	String UPLOADED_FOLDER = "D:\\UploadFile\\";

	@PostMapping(value = "/customers/create")
	public ResponseEntity<?> createUser(@ModelAttribute CustomerDetailsRequestModel customerDetails) throws IOException {

		try 
		{
			CustomerResponse custResponse = new CustomerResponse();
	
			ModelMapper modelMapper = new ModelMapper();
			CustomerDto customerDto = modelMapper.map(customerDetails, CustomerDto.class);
			
			customerDto.setLogo(customerDetails.getLogo().getOriginalFilename());
			
			CustomerDto createdCustomer = hcvsService.createCustomer(customerDto);
			custResponse = modelMapper.map(createdCustomer, CustomerResponse.class);
			
			saveUploadedFile(customerDetails.getLogo());
			
			return new ResponseEntity<>(custResponse, HttpStatus.OK);
		}
		catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	
	}
	
	private void saveUploadedFile(MultipartFile file) throws IOException {
		
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
        }
    }

	@DeleteMapping("/customers/{id}")
	public CustomerDto deleteCustomer(@PathVariable("id") long id) {
		System.out.println("Delete Customer with ID = " + id + "...");

		CustomerDto customerDto = hcvsService.deleteById(id);

		return customerDto;
	}

	@GetMapping("/customers/all")
	public List<CustomerEntity> getAllCustomers() {

		System.out.println("Get all Customers...");

		List<CustomerEntity> customers = hcvsService.findAll();

		return customers;
	}

	@PutMapping(value = "/customers")
	public ResponseEntity<?> updateCustomer(@ModelAttribute CustomerDetailsRequestModel customerDetails) {
		try 
		{
			System.out.println("Update Customer with ID = " + customerDetails.getId() + "...");
			
			ModelMapper modelMapper = new ModelMapper();
			CustomerDto customerDto = modelMapper.map(customerDetails, CustomerDto.class);
			
			customerDto.setLogo(customerDetails.getLogo().getOriginalFilename());
			
			CustomerDto custResponse = hcvsService.updateCustomer(customerDto);
			
			saveUploadedFile(customerDetails.getLogo());
			return new ResponseEntity<>(custResponse, HttpStatus.OK);
		}
		catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}
	
	@RequestMapping(value = "/customers/logo/{logoName}", method=RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> getCustomerLogo(@PathVariable("logoName") String name) throws IOException {
		
		//HttpHeaders headers = new HttpHeaders();
		//headers.setContentType(MediaType.IMAGE_JPEG);
		 
		File file = new File(UPLOADED_FOLDER + name);
	    return ResponseEntity.ok().contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(file))).body(Files.readAllBytes(file.toPath()));
		/*
		 * byte[] bFile = new byte[(int) file.length()]; System.out.println(bFile);
		 * 
		 * return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bFile);
		 */
        
        //return new ResponseEntity<byte[]>(bFile, headers, HttpStatus.OK);
	}

}
