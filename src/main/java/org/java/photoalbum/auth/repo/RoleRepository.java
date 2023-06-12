package org.java.photoalbum.auth.repo;

import org.java.photoalbum.auth.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
