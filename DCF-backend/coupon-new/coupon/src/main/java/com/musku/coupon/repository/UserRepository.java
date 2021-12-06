package com.musku.coupon.repository;

import com.musku.coupon.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends MongoRepository<User, Integer> {
    User findUsersById(int Id);

    User findByMobileNumber(String mobilenumber);

    User findByUserName(String username);
}
