package org.java.photoalbum.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.java.photoalbum.pojo.Category;
import org.java.photoalbum.pojo.Photo;
import org.java.photoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/sadmin/photos")
public class AdminPhotoController {

	@Autowired
	PhotoService photoService;
	
	@GetMapping
	public String index(@RequestParam(required = false) String title, Model model){
		
		List<Photo> photos = new ArrayList<>();
		
		if (title == null || title.isEmpty()) {
			photos = photoService.findAll();
			model.addAttribute("photos", photos);
		} else {
			photos = photoService.findByTitle(title);
			model.addAttribute("photos", photos);
			model.addAttribute("title", title);
		}
		
		
		return "index";
	}
	
	@GetMapping("/{id}")
	public String show(Model model, @PathVariable("id") Integer id) {
		
		Optional<Photo> oPhoto = photoService.findById(id);	
		
		if (oPhoto.isPresent()) {
			Photo photo = oPhoto.get();
			List<Category> categories = photo.getCategories();
			model.addAttribute("categories", categories);
			model.addAttribute("photo", photo);
		} else {
			return "redirect:/sadmin/photos";
		}
		
		return "photo";
	}
	
	
	@PostMapping("/{id}/visible")
	public String visibility(@PathVariable("id") Integer id) {
		Optional<Photo> oPhoto = photoService.findById(id);	
		
		if (oPhoto.isPresent()) {
			Photo photo = oPhoto.get();
			if (photo.isVisible()) {
				photo.setVisible(false);
			} else {
				photo.setVisible(true);
			}
			photoService.save(photo);
			
		}
		
		return "redirect:/sadmin/photos";
}
	
}
