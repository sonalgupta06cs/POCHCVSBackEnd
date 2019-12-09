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
import java.util.Optional;

import javax.activation.FileTypeMap;
import javax.servlet.http.HttpServletRequest;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.poc.hcvs.ui.model.request.CustomerDetailsRequestModel;
import com.poc.hcvs.ui.model.response.CustomerResponse;
import com.poc.hcvs.ws.config.JwtRequest;
import com.poc.hcvs.ws.config.JwtResponse;
import com.poc.hcvs.ws.config.JwtTokenUtil;
import com.poc.hcvs.ws.config.JwtUserDetailsService;
import com.poc.hcvs.ws.exceptions.ErrorMessages;
import com.poc.hcvs.ws.exceptions.HCVSCustomerServiceException;
import com.poc.hcvs.ws.model.CustomerEntity;
import com.poc.hcvs.ws.service.HCVSCustomerService;
import com.poc.hcvs.ws.shared.dto.CustomerDto;

/**
 * @author 143703
 *
 */
@CrossOrigin(origins = "http://localhost:4300")
@RestController
@RequestMapping(value = "/api")
public class HCVSCustomerController {

	@Autowired
	HCVSCustomerService hcvsService;

	String UPLOADED_FOLDER = "D:\\UploadFile\\";

	@PostMapping(value = "/customers/create")
	public ResponseEntity<?> createCustomer(@ModelAttribute CustomerDetailsRequestModel customerDetails,
			@RequestParam(value = "logo", required = false) Optional<MultipartFile> logo, HttpServletRequest request)
			throws IOException, Exception {

		if ((customerDetails.getTier1Name() == null || customerDetails.getTier1Name().isEmpty())
				&& (customerDetails.getCustomerName() == null || customerDetails.getCustomerName().isEmpty()))
			throw new HCVSCustomerServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

		CustomerResponse custResponse = new CustomerResponse();

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
		CustomerDto customerDto = modelMapper.map(customerDetails, CustomerDto.class);

		if (logo.isPresent()) {
			customerDto.setLogo(logo.get().getOriginalFilename());
		}

		System.out.println("IsSession "+request.getSession().getCreationTime());
		System.out.println(" session user name in create method "+request.getSession().getAttribute("userName"));
		
		CustomerDto createdCustomer = hcvsService.createCustomer(customerDto);
		custResponse = modelMapper.map(createdCustomer, CustomerResponse.class);

		if (logo.isPresent()) {
			saveUploadedFile(logo.get());
		}

		return new ResponseEntity<>(custResponse, HttpStatus.OK);

	}

	private void saveUploadedFile(MultipartFile file) throws IOException {

		if (!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);
		}
	}

	@PostMapping(value = "/customers")
	public String deleteCustomer(@RequestBody long[] ids) { // (@PathVariable("id") long id) {

		for (long id : ids) {
			System.out.println("Delete Customer with ID = " + id + "...");
		}

		if (ids.length == 0) {
			throw new HCVSCustomerServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		} else {

			int returnedRows = hcvsService.deleteById(ids);

			if (returnedRows == ids.length) {
				return "success";
			}
			return "faliure";
		}

//		  CustomerResponse custResponse = new CustomerResponse(); ModelMapper
//		  modelMapper = new ModelMapper();
//		  custResponse = modelMapper.map(customerDto,
//		  CustomerResponse.class);
//		  
//		  return custResponse;

	}

	@GetMapping("/customers/all")
	public List<CustomerResponse> getAllCustomers() {

		System.out.println("Get all Customers...");

		// List<CustomerResponse> custResponse = new ArrayList<>();
		// ModelMapper mapper = new ModelMapper();
		// hcvsService.findAll().stream().map(customer -> mapper.map(customer,
		// CustomerResponse.class)).forEach(custResponse::add);

		List<CustomerEntity> customers = hcvsService.findAll();

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(new PropertyMap<CustomerEntity, CustomerResponse>() {

			@Override
			protected void configure() {
				map().setUserId(source.getUserDetails().getId());
			}
		});

		// Define the target type
		java.lang.reflect.Type targetListType = new TypeToken<List<CustomerResponse>>() {}.getType();
		List<CustomerResponse> custResponse = modelMapper.map(customers, targetListType);

		return custResponse;
	}

	@PutMapping(value = "/customers")
	public ResponseEntity<?> updateCustomer(@ModelAttribute CustomerDetailsRequestModel customerDetails,
			@RequestParam(value = "active", required = false) Boolean active,
			@RequestParam(value = "logo", required = false) Optional<MultipartFile> file) throws Exception {

		System.out.println("Update Customer with ID = " + customerDetails.getId() + "...");

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
		CustomerDto customerDto = modelMapper.map(customerDetails, CustomerDto.class);

		if (active != null) {
			customerDto.setActive(active);
		}

		if (file.isPresent()) {
			customerDto.setLogo(file.get().getOriginalFilename());
		}

		CustomerDto custResponse = hcvsService.updateCustomer(customerDto);

		if (file.isPresent()) {
			saveUploadedFile(file.get());
		}
		return new ResponseEntity<>(custResponse, HttpStatus.OK);

	}

	@RequestMapping(value = "/customers/logo/{logoName}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> getCustomerLogo(@PathVariable("logoName") String name) throws IOException {

		// HttpHeaders headers = new HttpHeaders();
		// headers.setContentType(MediaType.IMAGE_JPEG);

		if (name == null || name.isEmpty())
			throw new HCVSCustomerServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

		File file = new File(UPLOADED_FOLDER + name);
		return ResponseEntity.ok()
				.contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(file)))
				.body(Files.readAllBytes(file.toPath()));
	}

	@GetMapping("/customers/search/{searchBy}/{searchText}")
	public List<CustomerEntity> getCustomersBySearch(@PathVariable("searchBy") String searchBy,
			@PathVariable("searchText") String searchText) {

		if ((searchBy == null || searchBy.isEmpty()) && (searchText == null || searchText.isEmpty()))
			throw new HCVSCustomerServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

		System.out.println("getCustomersBySearch..." + searchBy + " " + searchText);

		List<CustomerEntity> customersList = null;

		if (searchBy.equals("customerName")) {
			customersList = hcvsService.findByCustomerNameLike(searchText);
		} else if (searchBy.equals("tier1Name")) {
			customersList = hcvsService.findByTier1Like(searchText);
		} else if (searchBy.equals("tier2Name")) {
			customersList = hcvsService.findByTier2Like(searchText);
		}

		return customersList;
	}

	@GetMapping("/customers")
	public List<CustomerDto> getAllCustomersName(@RequestParam("keyword") String keyword) {
		return hcvsService.getAllCustomersName(keyword);
	}

	@GetMapping("/customers/{id}")
	public CustomerDto getCustomer(@PathVariable("id") Long id) {
		return this.hcvsService.findById(id);
	}
}
