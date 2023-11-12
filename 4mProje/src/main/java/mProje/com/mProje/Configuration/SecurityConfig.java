package mProje.com.mProje.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import mProje.com.mProje.service.JpaUserDetailsService;

@Configuration
@EnableWebSecurity

public class SecurityConfig {
	
	@Autowired
	private JpaUserDetailsService service;
	
	@Bean
	public SecurityFilterChain securityfilterChain(HttpSecurity http) throws Exception {
		
		return http.csrf(csrf ->csrf.disable()).authorizeRequests(auth ->auth
			//.antMatchers("/index").permitAll()
			//.antMatchers("/dashboard").permitAll()
			//.antMatchers("/profile").permitAll()
			//.antMatchers("/signup").permitAll()
			.antMatchers("/error").permitAll()
			.anyRequest().authenticated()		
		)
				.formLogin(Customizer.withDefaults())
				.userDetailsService(service)
				.build();
}
	
	@Bean
	PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}