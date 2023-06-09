package org.java.photoalbum.repo;

import org.java.photoalbum.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
