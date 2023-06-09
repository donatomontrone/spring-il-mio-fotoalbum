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
	
	public List<Photo> findByTitle(String title){
		return photoRepository.findByTitleContaining(title);
	}
	
	public Photo save(Photo photo) {
		return photoRepository.save(photo);
	}
	
	public void delete(Photo photo) {
		photoRepository.delete(photo);
	}
}
