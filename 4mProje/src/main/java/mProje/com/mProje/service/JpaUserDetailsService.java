package mProje.com.mProje.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import mProje.com.mProje.dto.UserDTO;
import mProje.com.mProje.entity.User;
import mProje.com.mProje.repository.UserRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return repo.findByUsername(username).map(UserDTO::new)
				.orElseThrow(() -> new UsernameNotFoundException("Aranan kullanıcı bulunamadı"));
	}

	public boolean checkEmail(String email) {

		return repo.existsByEmail(email);

	}

	public List<User> getAllUsers() {
		return repo.findAll();
	}

	public User add(User user) {
		return repo.save(user);
	}
}
