package org.java.photoalbum.pojo;


import java.util.Arrays;
import java.util.List;

import org.hibernate.validator.constraints.URL;
import org.java.photoalbum.auth.pojo.User;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Photo {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "Inserisci il titolo della foto")
	@NotBlank(message = "Inserisci il titolo della foto")
	@Size(min=3,max=255,message="Il titolo della foto deve essere compreso tra 3 e 255 caratteri")
	private String title;
	
	@Column(columnDefinition = "TEXT")
	@Size(max=65535, message="La descrizione della foto deve essere compresa tra 3 e 65535 caratteri")
	private String description;

	@URL(message = "L'URL della foto non è valido.")
	@NotNull(message="Inseriaci l'URL per la foto")
	@NotBlank(message="Inserisci l'URL per la foto.")
	@Size(max=255,message="L'URL della foto non può superare i 255 caratteri.")
	private String url;
	
	@NotNull(message="Definisci se la foto è o non è visibile")
	private boolean visible;
	
	@ManyToMany
	private List<Category> categories;
	
	@ManyToOne
	@JsonBackReference
	private User user;
	
	public Photo() {}
	
	public Photo(String title, String url, boolean visible,User user, Category...categories) {
		setTitle(title);
		setUrl(url);
		setVisible(visible);
		setUser(user);
		setCategories(categories);
	}
	
	public Photo(String title, String url, boolean visible, String description, User user, Category...categories) {
		this(title, url, visible, user, categories);
		setDescription(description);

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean hasDescription() {
		
		return getDescription() != null;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	@JsonIgnore
	public void setCategories(Category[] categories) {
		setCategories(Arrays.asList(categories));
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public void addCategory(Category category) {
		getCategories().add(category);
	}
	
	public void removeCategory(Category category) {
		getCategories().remove(category);
	}

	@Override
	public String toString() {
		return "[" + getId() + "] - Titolo: " + getTitle()
			+ "\nUrl: " + getUrl() + " - è visibile: " + (isVisible() ? "Sì" : "No")
			+ "\nDescrizione: " + (hasDescription() ? getDescription() : "");
	}
	
	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof Photo)) return false;

		Photo ingObject = (Photo) obj;

		return getId() == ingObject.getId();
	}
	@Override
	public int hashCode() {

		return getId();
	}
	
}
