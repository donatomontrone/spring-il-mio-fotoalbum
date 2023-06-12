package org.java.photoalbum.service;

import java.util.List;
import java.util.Optional;

import org.java.photoalbum.pojo.Photo;
import org.java.photoalbum.repo.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoService {

	@Autowired
	private PhotoRepository photoRepository;
	
	public List<Photo> findAll(){
		return photoRepository.findAll();
	}
	
	public Optional<Photo> findById(int id) {
		return photoRepository.findById(id);
	}
	
	public List<Photo> findByUsername(String username){
		return photoRepository.findByUserUsername(username);
	}
	
	public List<Photo> findByTitleAndUsername(String title, String username){
		return photoRepository.findByTitleContainingAndUserUsername(title, username);
	}
	
	public Optional<Photo> findByIdAndUsername(Integer id, String username){
		return photoRepository.findByIdAndUserUsername(id, username);
	}
	
	public Photo save(Photo photo) {
		return photoRepository.save(photo);
	}
	
	public void delete(Photo photo) {
		photoRepository.delete(photo);
	}
}
