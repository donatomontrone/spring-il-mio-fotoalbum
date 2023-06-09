package org.java.photoalbum.service;

import org.java.photoalbum.pojo.Contact;
import org.java.photoalbum.repo.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

	@Autowired
	private ContactRepository contactRepository;
	
	public Contact save(Contact contact) {
		return contactRepository.save(contact);
	}
}
