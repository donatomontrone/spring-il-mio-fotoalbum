package org.java.photoalbum;


import java.util.Arrays;
import java.util.List;

import org.java.photoalbum.auth.pojo.Role;
import org.java.photoalbum.auth.pojo.User;
import org.java.photoalbum.auth.service.RoleService;
import org.java.photoalbum.auth.service.UserService;
import org.java.photoalbum.pojo.Category;
import org.java.photoalbum.pojo.Photo;
import org.java.photoalbum.service.CategoryService;
import org.java.photoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class PhotoAlbumApplication  implements CommandLineRunner{

	@Autowired
	RoleService roleService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	PhotoService photoService;
	
	@Autowired
	CategoryService categoryService;
	
	
	public static void main(String[] args) {
		SpringApplication.run(PhotoAlbumApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
//		RUOLI
		List<Role> roles = Arrays.asList(
				new Role("ADMIN"),
				new Role("SUPERADMIN"),
				new Role("USER")
				);
		
		for (Role role : roles) {
			roleService.save(role);
		}
		
//		UTENTI
		
		final String psw = new BCryptPasswordEncoder().encode("11223344");
		
		List<User> users = Arrays.asList(
				new User("admin1", psw, roles.get(0)),
				new User("admin2", psw, roles.get(0)),
				new User("god", psw, roles.get(1)),
				new User("user", psw, roles.get(2))
				);
		
		for (User user : users) {
			userService.save(user);
		}
		
//	CATEGORIE
		
		List<Category>  categories = Arrays.asList(
				new Category("Animali"),
				new Category("Persone"),
				new Category("Natura"),
				new Category("Eventi"),
				new Category("Cibi e bevande"),
				new Category("Architettura")
				);
		
		for (Category category : categories) {
			categoryService.save(category);
		}
		
//		FOTO"https://picsum.photos/id/1/1000/500"
		List<Photo> photos = Arrays.asList(
				new Photo("New York", "https://picsum.photos/id/299/1000/500", true, "Vista dell'Empire State Building", users.get(0), categories.get(5)),
				new Photo("Pecore", "https://picsum.photos/id/699/1000/500", true, "Pecore al pascolo", users.get(0), categories.get(0), categories.get(1), categories.get(2)),
				new Photo("Sunset girl", "https://picsum.photos/id/399/1000/500", false, "Ritratto di una sconosciuta al tramonto", users.get(1),categories.get(2), categories.get(1)),
				new Photo("Mangiando", "https://picsum.photos/id/395/1000/500", true, "Ho scoperto i miglior caffè di Manhattan", users.get(0), categories.get(1), categories.get(4)),
				new Photo("Aquila Reale", "https://picsum.photos/id/538/1000/500", true, users.get(0), categories.get(0), categories.get(2)),
				new Photo("Spring Break", "https://picsum.photos/id/449/1000/500", true, "Estauste dopo la festa sulla spiaggia dello Spring Break", users.get(0), categories.get(1), categories.get(2), categories.get(3)),
				new Photo("Cascate", "https://picsum.photos/id/573/1000/500", true, users.get(0), categories.get(2)),
				new Photo("More", "https://picsum.photos/id/429/1000/500", false, users.get(0), categories.get(4)),
				new Photo("Cupo", "https://picsum.photos/id/719/1000/500", true, "Un chiesa immersa nella natura",  users.get(1), categories.get(2), categories.get(5)),
				new Photo("Capodanno", "https://picsum.photos/id/407/1000/500", true, users.get(1), categories.get(3), categories.get(1)),
				new Photo("Surf Week", "https://picsum.photos/id/643/1000/500", true, "Fine della Surf Week 2023", users.get(0), categories.get(2), categories.get(3)),
				new Photo("Alla ricerca del cibo", "https://picsum.photos/id/611/1000/500", false, users.get(1), categories.get(0), categories.get(2), categories.get(4)),
				new Photo("Best friends", "https://picsum.photos/id/494/1000/500", true, "Alessio con il suo cane", users.get(1), categories.get(0), categories.get(1)),
				new Photo("Osservare", "https://picsum.photos/id/338/1000/500", false, "Osservando e passeggiando sulla spiaggia", users.get(1), categories.get(2), categories.get(1))
				);
		
		for (Photo photo : photos) {
			photoService.save(photo);
		}

	}

}
