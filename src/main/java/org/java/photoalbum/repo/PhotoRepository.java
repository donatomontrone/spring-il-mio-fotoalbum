package org.java.photoalbum.repo;

import java.util.List;

import org.java.photoalbum.pojo.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Integer>{
	
	public List<Photo> findByTitleContaining(String title);

}
