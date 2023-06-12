package org.java.photoalbum.auth.service;

import java.util.List;
import java.util.Optional;

import org.java.photoalbum.auth.pojo.Role;
import org.java.photoalbum.auth.repo.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

	
	@Autowired
	private RoleRepository roleRepository;
	
	public List<Role> findAll(){
		
		return roleRepository.findAll();
	}
	
	public Optional<Role> findById(int id){
		return roleRepository.findById(id);
	}
	
	public Role save(Role role) {
		return roleRepository.save(role);
	}
}
