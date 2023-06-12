package org.java.photoalbum.auth.service;

import java.util.List;
import java.util.Optional;

import org.java.photoalbum.auth.pojo.User;
import org.java.photoalbum.auth.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public Optional<User> findById(int id){
		return userRepository.findById(id);
	}
	
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> oUser = userRepository.findByUsername(username);
		
		if (oUser.isEmpty()) {
			throw new UsernameNotFoundException("Utente non trovato");
		}
		
		User user = oUser.get();
		return user;
	}
	
}
