package org.java.photoalbum.controller;

import java.util.List;
import java.util.Optional;

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

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	PhotoService photoService;
	
	@GetMapping
	public String index(Model model) {
		
		List<Category> categories = categoryService.findAll();
		
		model.addAttribute("categories",categories);
		
		
		return "index-categories";
	}
	
	@GetMapping("/create")
	public String create(Model model, Authentication authentication) {
		String username = authentication.getName();
		List<Photo> photos = photoService.findByUsername(username);
		
		
		model.addAttribute("category", new Category());
		model.addAttribute("photos", photos);
		return "create-category";
	}
	
	@PostMapping("/create")
	public String store(Model model, @ModelAttribute @Valid Category category, BindingResult bindingResult, Authentication authentication) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("errors", bindingResult);
			model.addAttribute("category", category);
			
			return "create-category";
		}	
		
		String username = authentication.getName();
			categoryService.save(category);
			List<Photo> photos = category.getPhotos();
			if (photos != null) {
				for (Photo p : photos) {
					Photo tmpPhoto = photoService.findByIdAndUsername(p.getId(), username).get();
					tmpPhoto.addCategory(category);
					photoService.save(tmpPhoto);
				}
			}
		
		
			return "redirect:/admin/categories";
		}
	
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		Optional<Category> oCategory = categoryService.findById(id);
		Category category = oCategory.get();
		
		for (Photo photo : category.getPhotos()) {
			Photo tmpPhoto = photoService.findById(photo.getId()).get();
			tmpPhoto.removeCategory(category);
			photoService.save(tmpPhoto);
		}
		categoryService.delete(category);
		return "redirect:/admin/categories";
	}
	
}
