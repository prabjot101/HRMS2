package com.example.ems.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class USerServiceimpl implements UserServices{

    @Autowired
    private UsersDao userDao;



    public USerServiceimpl()
    {

    }

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {


        String name = file.getOriginalFilename();
        String filePath = path + File.separator+name;
        File f = new File(path);

        if(!f.exists())
        {
            f.mkdir();
        }

        Files.copy(file.getInputStream(), Paths.get(filePath));


        return name;

    }

    @Override
    public Users loadImage(Users fr) {
        userDao.save(fr);
        return fr;
    }

    @Override
    public List<Users> findAll() {
        return userDao.findAll();
    }


    public List<Users> findByID(long key) {
        return userDao.findById(key);
    }


    @Override
    public List<Users> getUsers() {
        return userDao.findAll();
    }

    @Override
    public Users getUsers(long id) {
        return (Users) userDao.findById(id);
    }



    @Override
    public Users addUser(Users user) {
        userDao.save(user);
        return user;
    }

    @Override
    public Users updateUsers(Users user) {
        userDao.save(user);
        return user;
    }

    @Override
    public void deleteUser(long id) {


            userDao.deleteById(id);


    }




    ////For list


  //  List<Users> users;

//    public USerServiceimpl()
//    {
//        users = new ArrayList<>();
//        users.add(new Users(1, "rajat", "email", "vancouver", "canada", "234"));
//
//
//    }
//
//    @Override
//    public List<Users> getUsers() {
//        return users;
//    }
//
//    @Override
//    public Users getUsers(int id) {
//        Users u = null;
//
//        for(Users user : users)
//        {
//            if(user.getId() == id)
//            {
//                u = user;
//                break;
//            }
//        }
//
//        return u;
//    }
//
//    @Override
//    public Users addUser(Users user) {
//        users.add(user);
//        return user;
//    }
//
//    @Override
//    public Users updateUsers(Users user) {
//        return null;
//    }


}
