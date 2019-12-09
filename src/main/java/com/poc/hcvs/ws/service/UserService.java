/*
 * package com.poc.hcvs.ws.service;
 * 
 * import java.util.Optional;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.security.core.userdetails.UserDetails; import
 * org.springframework.security.core.userdetails.UserDetailsService; import
 * org.springframework.security.core.userdetails.UsernameNotFoundException;
 * import org.springframework.stereotype.Service;
 * 
 * import com.poc.hcvs.ws.model.UserEntity; import
 * com.poc.hcvs.ws.repository.UserRepository; import
 * com.poc.hcvs.ws.service.impl.UserServiceImpl;
 * 
 * @Service public class UserService implements UserDetailsService {
 * 
 * @Autowired UserRepository userRepository;
 * 
 * @Override public UserDetails loadUserByUsername(String email) throws
 * UsernameNotFoundException {
 * 
 * Optional<UserEntity> userFound = userRepository.findByEmail(email);
 * 
 * userFound.orElseThrow(() -> new
 * UsernameNotFoundException("User Not found : "+email));
 * 
 * return userFound.map(UserServiceImpl::new).get();
 * 
 * }
 * 
 * }
 */