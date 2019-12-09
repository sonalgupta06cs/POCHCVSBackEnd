package com.poc.hcvs.ws.config;
/**
 * 
 *//*
	 * package com.poc.hcvs.ws.security;
	 * 
	 * import org.springframework.beans.factory.annotation.Autowired; import
	 * org.springframework.context.annotation.Bean; import
	 * org.springframework.http.HttpMethod; import
	 * org.springframework.security.config.annotation.authentication.builders.
	 * AuthenticationManagerBuilder; import
	 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
	 * import org.springframework.security.config.annotation.web.configuration.
	 * EnableWebSecurity; import
	 * org.springframework.security.config.annotation.web.configuration.
	 * WebSecurityConfigurerAdapter; import
	 * org.springframework.security.core.userdetails.UserDetailsService; import
	 * org.springframework.security.crypto.password.NoOpPasswordEncoder; import
	 * org.springframework.security.crypto.password.PasswordEncoder;
	 * 
	 * @EnableWebSecurity public class WebSecurity extends
	 * WebSecurityConfigurerAdapter {
	 * 
	 * @Autowired UserDetailsService userDetailsService;
	 * 
	 * //@Autowired //private BCryptPasswordEncoder bCryptPasswordEncoder;
	 * 
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * 
	 * http.csrf().disable(). // dont authenticate this particular request
	 * authorizeRequests() .antMatchers(HttpMethod.POST,
	 * "/api/login/authenticate").permitAll() .antMatchers(HttpMethod.OPTIONS,
	 * "/**").permitAll() // all other requests need to be authenticated
	 * .anyRequest().authenticated() .and().httpBasic(); }
	 * 
	 * @Autowired public void configureGlobal(AuthenticationManagerBuilder auth)
	 * throws Exception { //
	 * auth.inMemoryAuthentication().withUser("javainuse").password("{noop}password"
	 * ).roles("USER");
	 * 
	 * // Spring Security which looks up for a user from Database based on
	 * userdetails implementation // as soon as somebody tries to authenticate, then
	 * call comes to this function.
	 * auth.userDetailsService(userDetailsService);//.passwordEncoder(
	 * bCryptPasswordEncoder); }
	 * 
	 * @Bean public PasswordEncoder getPasswordEncoder() { return
	 * NoOpPasswordEncoder.getInstance(); }
	 * 
	 * }
	 */