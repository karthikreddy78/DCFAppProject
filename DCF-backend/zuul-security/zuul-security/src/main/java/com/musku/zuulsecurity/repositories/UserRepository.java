package com.musku.zuulsecurity.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.musku.zuulsecurity.models.User;

public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);
}