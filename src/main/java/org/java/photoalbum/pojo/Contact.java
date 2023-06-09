package org.java.photoalbum.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Contact {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Email(message="Inserisci un email valida")
	@NotNull(message="Inserisci la tua email")
	private String email;
	
	
	@NotNull(message="Inserisci il messaggio che vuoi inviarci")
	@NotBlank(message="Inserisci il messaggio che vuoi inviarci")
	@Size(max=255,message="Il messaggio non deve essere superare 255 caratteri")
	private String message;
	
	
	public Contact() {}
	
	public Contact(String email, String message) {
		setEmail(email);
		setMessage(message);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "[" + getId() + "] - email: " + getEmail() + " - messaggio: " + getMessage();
	}
}