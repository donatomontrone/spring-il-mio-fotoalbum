package org.java.photoalbum.api.controller;

import org.java.photoalbum.pojo.Contact;
import org.java.photoalbum.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class ContactApiController {
	
	@Autowired
	ContactService contactService;
	
	@PostMapping("/contact")
	public ResponseEntity<Contact> create(@RequestBody Contact contact){

		contactService.save(contact);
		
		return new ResponseEntity<>(contact, HttpStatus.OK);
	}

}
