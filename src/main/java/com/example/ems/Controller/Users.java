package com.example.ems.Controller;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name= "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String Name;

    private String Email;

    private String city;

    private String country;

    private String Phone;

    private byte[] image;

    private String fileName;

    private String message;

    private String Age;

    private String position;

    private String Salary;


    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String salary) {
        Salary = salary;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public Users(long id, String name, String email, String city, String country, String phone) {
        this.id = id;
        Name = name;
        Email = email;
        this.city = city;
        this.country = country;
        Phone = phone;
    }

    public Users(String fileName, String message, byte[] img) {
        this.fileName = fileName;
        this.message = message;
        this.image = img;
    }

    public Users()
    {

    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", Email='" + Email + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", Phone='" + Phone + '\'' +
                ", image=" + Arrays.toString(image) +
                ", fileName='" + fileName + '\'' +
                ", message='" + message + '\'' +
                ", Age='" + Age + '\'' +
                ", position='" + position + '\'' +
                ", Salary='" + Salary + '\'' +
                '}';
    }
}
