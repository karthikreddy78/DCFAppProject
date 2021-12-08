package com.musku.admin.proxy;

import com.musku.admin.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "user-service")
public interface UserFeignProxy {

    @GetMapping(path="/users/getuserlist")
    public List<User> showUsers();
}
