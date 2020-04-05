package com.saml.travellersapp.repository;

import com.saml.travellersapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer> {

    @Override
    List<User> findAll();

    User findById(int id);

    List<User> findByDob(LocalDate dateOfBirth);

    List<User> findByName(String name);

}
