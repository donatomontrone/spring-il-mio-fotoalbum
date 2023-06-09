package org.java.photoalbum.service;

import java.util.List;
import java.util.Optional;

import org.java.photoalbum.pojo.Category;
import org.java.photoalbum.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> findAll(){
		return categoryRepository.findAll();
	}
	
	public Optional<Category> findById(int id) {
		return categoryRepository.findById(id);
	}

	
	public Category save(Category category) {
		return categoryRepository.save(category);
	}
	
	public void delete(Category category) {
		categoryRepository.delete(category);
	}
}
