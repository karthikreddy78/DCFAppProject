package com.musku.admin.service;

import com.musku.admin.entity.Admin;
import com.musku.admin.entity.User;
import com.musku.admin.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.musku.admin.entity.User.SEQUENCE_NAME;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private SequenceGeneratorService service;

   public List<Admin> getAll(){
        return adminRepository.findAll();
    }

    public List<Admin> getAdmin(){
        return adminRepository.findByRole("admin");
    }
//
//    public User findUsersById(int id) {
//        return userRepository.findUsersById(id);
//    }
//
//    public User findByUsername(String username) {
//        return userRepository.findByUserName(username);
//    }

    public Admin create(Admin u) {
        u.setId(service.getSequenceNumber(SEQUENCE_NAME));
        return adminRepository.save(u);
    }

    public Admin updateById(Admin u,int id) {
        Admin u1=adminRepository.findUsersById(id);
        if(u1==null)
        {
            return null;
        }
        if(u.getFirstName()!=null)
            u1.setFirstName(u.getFirstName());
        if(u.getLastName()!=null)
            u1.setLastName(u.getLastName());
        if(u.getAge()!=(null))
            u1.setAge(u.getAge());
        if(u.getGender()!=(null))
            u1.setGender(u.getGender());
        if(u.getPassword()!=(null))
            u1.setPassword(u.getPassword());
        if(u.getRole()!=null)
        {
            u1.setRole(u.getRole());
        }
        return adminRepository.save(u1);
    }

    public Admin updateByAdminname(Admin u, String id) {
        Admin u1=adminRepository.findByUserName(id);
        if(u1==null)
        {
            return null;
        }
        if(u.getFirstName()!=null)
            u1.setFirstName(u.getFirstName());
        if(u.getLastName()!=null)
            u1.setLastName(u.getLastName());
        if(u.getAge()!=(null))
            u1.setAge(u.getAge());
        if(u.getGender()!=(null))
            u1.setGender(u.getGender());
        if(u.getPassword()!=(null))
            u1.setPassword(u.getPassword());
        if(u.getRole()!=null)
        {
            u1.setRole(u.getRole());
        }
        return adminRepository.save(u1);
    }

    public Admin deleteAdminById(int userId) {
        Admin p =adminRepository.findUsersById(userId);
        if(p==null)
            return null;
        adminRepository.delete(p);
        return p;
    }

    public Admin deleteAdminByUsername(String id) {
        Admin p=adminRepository.findByUserName(id);
        if(p==null)
            return null;
        adminRepository.delete(p);
        return p;
    }


}
