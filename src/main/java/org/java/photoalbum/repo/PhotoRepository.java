package org.java.photoalbum.repo;

import java.util.List;
import java.util.Optional;

import org.java.photoalbum.pojo.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Integer>{

	List<Photo> findByUserUsername(String username);

	List<Photo> findByTitleContainingAndUserUsername(String title, String username);
	
	Optional<Photo> findByIdAndUserUsername(Integer id, String username);

}
