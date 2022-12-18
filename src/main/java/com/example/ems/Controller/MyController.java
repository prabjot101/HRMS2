package com.example.ems.Controller;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;



@RestController
public class MyController {




    @Autowired
    private UserServices userServices;



    @GetMapping("/users")
    public List<Users> getUsers()
    {
        return this.userServices.getUsers();
    }

    @GetMapping("/users/{id}")
    public Users getCourse(@PathVariable String id)
    {
        return this.userServices.getUsers(Long.parseLong(id));
    }

    @PostMapping("/addUser")
    public Users addUser(@RequestBody Users user)
    {
        return this.userServices.addUser(user);
    }


    @Value("${project.image}")
    private String path;


    @PostMapping("/upload")
    public ResponseEntity<Users> fileUpload(@RequestParam("image") MultipartFile image,
                                            @RequestParam("message") String message,
                                            @RequestParam("Name") String Name,
                                            @RequestParam("Email") String Email,
                                            @RequestParam("city") String city,
                                            @RequestParam("country")String country,
                                            @RequestParam("Phone") String Phone,
                                            @RequestParam("age") String age ,
                                            @RequestParam("salary") String salary,
                                            @RequestParam("position") String position)
                                            throws IOException {

        String fileName = null;
        Users fr = new Users();
        try {
            fileName = this.userServices.uploadImage(path, image);
            fr.setName(Name);
            fr.setEmail(Email);
            fr.setCity(city);
            fr.setCountry(country);
            fr.setPhone(Phone);
            fr.setAge(age);
            fr.setSalary(salary);
            fr.setPosition(position);
            fr.setFileName(fileName);
            fr.setImage(image.getBytes());
            fr.setMessage(message);

            this.userServices.loadImage(fr);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new Users(null, "unsuccessfull", image.getBytes()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(new Users(fileName, "Successfully uploaded", image.getBytes()), HttpStatus.OK);

    }



    @PutMapping("/users")
    public Users updateUsers(@RequestBody Users user)
    {
        return this.userServices.updateUsers(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUsers(@PathVariable(value = "id") Long id) {
        userServices.deleteUser(id);
    }







}

