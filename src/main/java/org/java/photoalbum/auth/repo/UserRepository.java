package org.java.photoalbum.auth.repo;

import java.util.Optional;

import org.java.photoalbum.auth.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
	public Optional<User> findByUsername(String username);
}
