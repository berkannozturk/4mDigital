package mProje.com.mProje;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import mProje.com.mProje.entity.User;
import mProje.com.mProje.repository.UserRepository;
import mProje.com.mProje.service.JpaUserDetailsService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Autowired
	private JpaUserDetailsService service;

	@Bean
	CommandLineRunner runner(UserRepository repo,PasswordEncoder encode) {
		return args ->{
			if(!service.checkEmail("ali@x.com")) {
				repo.save(new User(null,"ali","veli","ali@x.com","ali@x.com",encode.encode("123"),"ROLE_USER",true,LocalDateTime.now()));
			}
			if(!service.checkEmail("fatma@x.com")) {
				repo.save(new User(null,"fatma","şen","fatma@x.com","fatma@x.com",encode.encode("456"),"ROLE_USER,ROLE_ADMIN",true,LocalDateTime.now()));
			}
	};
	}
}
