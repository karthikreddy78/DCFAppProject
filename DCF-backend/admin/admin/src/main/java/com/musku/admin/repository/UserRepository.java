package com.musku.admin.repository;

import com.musku.admin.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends MongoRepository<User, Integer> {
    User findUsersById(int Id);

    User findByMobileNumber(String mobilenumber);

    User findByUserName(String username);
}