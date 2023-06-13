package org.java.photoalbum.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.java.photoalbum.pojo.Photo;
import org.java.photoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/photos")
public class PhotoApiController {

	@Autowired 
	private PhotoService photoService;
	
	@GetMapping
	public ResponseEntity<List<Photo>> index(@RequestParam(required=false) String title){
		List<Photo> photos = new ArrayList<>();
		
		if (title == null || title.isEmpty()) {
			photos = photoService.findAll();
		} else {
			photos = photoService.findByTitle(title);	
		}
		
		List<Photo> vPhotos = new ArrayList<>();
		
		for (Photo photo : photos) {
			if (photo.isVisible()) {
				vPhotos.add(photo);				
			}
		}
 		
		return new ResponseEntity<>(vPhotos,HttpStatus.OK);
	}
	
	
}
