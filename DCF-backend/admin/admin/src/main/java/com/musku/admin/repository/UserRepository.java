package com.musku.admin.repository;


import com.musku.admin.entity.Role;
import com.musku.admin.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Set;

public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);
    List<User> findByRolesIn(Set<Role> roles);

}