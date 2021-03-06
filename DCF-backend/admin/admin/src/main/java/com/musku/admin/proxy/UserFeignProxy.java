//package com.musku.admin.proxy;
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//import java.util.List;
//
//@FeignClient(name = "users")
//public interface UserFeignProxy {
//
//    @GetMapping(path="/users/getuserlist")
//    public List<User> showUsers();
//
//    @DeleteMapping(path="/users/deletebyname/{id}")
//    public User deleteUserByUsername(@PathVariable String id);
//}
