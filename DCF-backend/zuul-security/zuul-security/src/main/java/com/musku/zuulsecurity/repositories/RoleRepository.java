package com.musku.zuulsecurity.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.musku.zuulsecurity.models.Role;

public interface RoleRepository extends MongoRepository<Role, String> {

    Role findByRole(String role);
}