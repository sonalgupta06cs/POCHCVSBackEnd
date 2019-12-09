/*
 * package com.poc.hcvs.ws.service.impl;
 * 
 * import java.util.Arrays; import java.util.Collection; import java.util.List;
 * import java.util.stream.Collectors;
 * 
 * import org.springframework.security.core.GrantedAuthority; import
 * org.springframework.security.core.authority.SimpleGrantedAuthority; import
 * org.springframework.security.core.userdetails.UserDetails;
 * 
 * import com.poc.hcvs.ws.model.UserEntity;
 * 
 * public class UserServiceImpl implements UserDetails {
 * 
 * private String fullName; private String email; private String password;
 * private List<GrantedAuthority> authorities;
 * 
 * public UserServiceImpl() {
 * 
 * }
 * 
 * public UserServiceImpl(UserEntity userFound) { this.fullName =
 * userFound.getFullName(); this.email = userFound.getEmail(); this.password =
 * userFound.getPassword(); this.authorities =
 * Arrays.stream(userFound.getRoleType().split(",")).map(SimpleGrantedAuthority:
 * :new) .collect(Collectors.toList()); }
 * 
 * @Override public Collection<? extends GrantedAuthority> getAuthorities() {
 * 
 * return authorities; }
 * 
 * @Override public String getPassword() { return password; }
 * 
 * @Override public String getUsername() { return email; }
 * 
 * @Override public boolean isAccountNonExpired() { return true; }
 * 
 * @Override public boolean isAccountNonLocked() { return true; }
 * 
 * @Override public boolean isCredentialsNonExpired() { return true; }
 * 
 * @Override public boolean isEnabled() { return true; }
 * 
 * }
 */