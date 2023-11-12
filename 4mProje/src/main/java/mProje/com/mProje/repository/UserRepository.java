package mProje.com.mProje.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import mProje.com.mProje.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByUsername(String username);

	boolean existsByEmail(String email);

}
