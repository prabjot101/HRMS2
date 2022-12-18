package com.example.ems.Controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsersDao extends JpaRepository<Users, Long> {



//    public void FindByName(String name);

    List<Users> findById (long kw);

}
