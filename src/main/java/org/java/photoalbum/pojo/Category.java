package org.java.photoalbum.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Category {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message="Inserisci il nome della categoria")
	@NotBlank(message="Inserisci il nome della categoria")
	@Size(min=3,max=255, message= "Il nome della categoria deve contenere da 3 a 255 caratteri")
	private String name;
	
	@ManyToMany(mappedBy = "categories")
	@JsonBackReference
	private List<Photo> photos;
	
	public Category() {}
	
	public Category(String name) {
		setName(name);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	@Override
	public String toString() {
		return "[" + getId() + "] - Nome: " + getName();
	}
	
	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof Category)) return false;

		Category ingObject = (Category) obj;

		return getId() == ingObject.getId();
	}
	@Override
	public int hashCode() {

		return getId();
	}
}
