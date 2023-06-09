package org.java.photoalbum.repo;

import org.java.photoalbum.pojo.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Integer>{

}
