package com.example.ems.Controller;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public interface UserServices {

    public List<Users> getUsers();

    public Users getUsers(long id);

    public  Users addUser(Users user);

    public Users updateUsers(Users user);

    public void deleteUser(long id);

    String uploadImage(String path, MultipartFile file) throws IOException;

    public Users loadImage(Users fr);


    List<Users> findAll();

    List<Users> findByID(long key);



}
