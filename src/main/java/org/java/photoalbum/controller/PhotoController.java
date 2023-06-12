package org.java.photoalbum.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.java.photoalbum.auth.pojo.User;
import org.java.photoalbum.auth.service.UserService;
import org.java.photoalbum.pojo.Category;
import org.java.photoalbum.pojo.Photo;
import org.java.photoalbum.service.CategoryService;
import org.java.photoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/photos")
public class PhotoController {

	
	@Autowired
	PhotoService photoService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CategoryService categoryService;
	
	
	@GetMapping
	public String index(@RequestParam(required = false) String title, Model model, Authentication authentication){
		String username = authentication.getName();
		
		List<Photo> photos = new ArrayList<>();
		
		if (title == null || title.isEmpty()) {
			photos = photoService.findByUsername(username);
			model.addAttribute("photos", photos);
		} else {
			photos = photoService.findByTitleAndUsername(title, username);
			model.addAttribute("photos", photos);
			model.addAttribute("title", title);
		}
		
		
		return "index";
	}
	
	
	
	
	@GetMapping("/{id}")
	public String show(Model model, @PathVariable("id") Integer id, Authentication authentication) {
		String username = authentication.getName();
		
		Optional<Photo> oPhoto = photoService.findByIdAndUsername(id, username);	
		
		if (oPhoto.isPresent()) {
			Photo photo = oPhoto.get();
			List<Category> categories = photo.getCategories();
			model.addAttribute("categories", categories);
			model.addAttribute("photo", photo);
		} else {
			return "redirect:/admin/photos";
		}
		
		return "photo";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		
		List<Category> categories = categoryService.findAll();
		
		model.addAttribute("photo", new Photo());
		model.addAttribute("categories",categories);
		
		return "photo-create";
	}
	
	@PostMapping("/create")
	public String store(Model model, @ModelAttribute @Valid Photo photo, BindingResult bindingResult, Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("errors", bindingResult);
			model.addAttribute("photo", photo);
			
			return "photo-create";
		}	
			photo.setUser(user);
			
			photoService.save(photo);
		
		
			return "redirect:/admin/photos/" + photo.getId();
		}
	
	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id, Authentication authentication) {
		String username = authentication.getName();
		
		Optional<Photo> oPhoto = photoService.findByIdAndUsername(id, username);
		
		if (oPhoto.isPresent()) {
			
			Photo photo = oPhoto.get();
			model.addAttribute("photo",photo);			
			return "photo-edit";
		}
		
		return "redirect:/admin/photos";
	}
	
	@PostMapping("/edit/{id}")
	public String update(Model model, @ModelAttribute @Valid Photo photo, BindingResult bindingResult, @PathVariable("id") Integer id) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("errors", bindingResult);
			model.addAttribute("photo", photo);
			
			return "photo-create";
		}	
		
			photoService.save(photo);
		
		
			return "redirect:/admin/photos/" + photo.getId();
		}
	
	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") Integer id, Authentication authentication) {
		String username = authentication.getName();
		Optional<Photo> oPhoto = photoService.findByIdAndUsername(id, username);
		
		if (oPhoto.isPresent()) {
			Photo photo = oPhoto.get();
			photoService.delete(photo);
		}
		
		return "redirect:/admin/photos";
	}
}
