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
		
//		FOTO
		List<Photo> photos = Arrays.asList(
				new Photo("Gattino", "https://picsum.photos/200", true, users.get(0), categories.get(0)),
				new Photo("Panda", "https://picsum.photos/200", true, "Un panda, immerso nella natura, che mangia foglie di bambu", users.get(0), categories.get(0)),
				new Photo("Sconosciuta", "https://picsum.photos/200", false, "Ritratto di una sconosciuta", users.get(0), categories.get(1)),
				new Photo("Brindisi", "https://picsum.photos/200", true, "Un brindisi al matrimonio del mio migliore amico", users.get(0), categories.get(1), categories.get(3)),
				new Photo("Preparazione", "https://picsum.photos/200", true, users.get(0), categories.get(4)),
				new Photo("Gareggiando", "https://picsum.photos/200", true, "Immortalando Alessia con il suo cavallo dopo aver gareggiato", users.get(0), categories.get(0), categories.get(1), categories.get(3)),
				new Photo("Cascate", "https://picsum.photos/200", true, users.get(0), categories.get(2)),
				new Photo("Gelato", "https://picsum.photos/200", false, "Il miglior gelato in circolazione!", users.get(0), categories.get(4)),
				new Photo("Bosco", "https://picsum.photos/200", true, "Un edificio che si trasforma in bosco",  users.get(1), categories.get(2), categories.get(5)),
				new Photo("Lezione", "https://picsum.photos/200", true, "Un meeting di archittetura", users.get(0), categories.get(1), categories.get(5), categories.get(3)),
				new Photo("GP Monaco", "https://picsum.photos/200", true, "Durante il Gran Premio di Monaco", users.get(0), categories.get(1), categories.get(3)),
				new Photo("Natura", "https://picsum.photos/200", false, "Immerso nella natura", users.get(1), categories.get(0), categories.get(2), categories.get(4)),
				new Photo("Westminster", "https://picsum.photos/200", true, "Abbazzia di Westminster", users.get(1), categories.get(5)),
				new Photo("Osservare", "https://picsum.photos/200", false, "Cercando le Torre Eiffel", users.get(1), categories.get(1), categories.get(5))
				);
		
		for (Photo photo : photos) {
			photoService.save(photo);
		}

	}

}
