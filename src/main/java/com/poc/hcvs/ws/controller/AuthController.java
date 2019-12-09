package com.poc.hcvs.ws.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poc.hcvs.ws.config.JwtRequest;
import com.poc.hcvs.ws.config.JwtResponse;
import com.poc.hcvs.ws.config.JwtTokenUtil;
import com.poc.hcvs.ws.config.JwtUserDetailsService;
import com.poc.hcvs.ws.exceptions.ErrorMessages;
import com.poc.hcvs.ws.exceptions.HCVSCustomerServiceException;


@CrossOrigin(origins = "http://localhost:4300")
@RestController
@RequestMapping("/api")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@PostMapping("/login/authenticate")
	public ResponseEntity<?> authenticateUser(@RequestBody JwtRequest authenticationRequest, HttpServletRequest request) throws Exception {

		if (authenticationRequest.getEmail() != null && authenticationRequest.getPassword() != null) {
			/*
			 * request.getSession().setAttribute("userName",
			 * loginViewModelRequest.getEmail()); ModelMapper mapper = new ModelMapper();
			 * UserResponse response = mapper.map(loginViewModelRequest,
			 * UserResponse.class);
			 * System.out.println(" session user name "+request.getSession().getAttribute(
			 * "userName"));
			 * //return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
			 */
			
			authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

			final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
			
			if(userDetails != null) {
				final String token = jwtTokenUtil.generateToken(userDetails);

			    return ResponseEntity.ok(new JwtResponse(token, userDetails.getUsername()));
			}
			    return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
			// Code to Generate the Json Web Token.	
		} else {
			
			   throw new HCVSCustomerServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		}

	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}
