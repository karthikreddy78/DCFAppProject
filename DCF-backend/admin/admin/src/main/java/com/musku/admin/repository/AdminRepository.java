package com.musku.admin.repository;

import com.musku.admin.entity.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends MongoRepository<Admin,Integer> {
    Admin findUsersById(int Id);
    Admin findByUserName(String username);
    List<Admin> findByRole(String role);

}
