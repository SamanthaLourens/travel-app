package com.saml.travellersapp.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private int id;

    @Column(name = "USER_NAME")
    private String name;

    @Column(name = "USER_SEX")
    private char sex;

    @Column(name = "USER_DOB")
    private LocalDate dob;

    @ManyToMany
    @JsonIgnore
    private Set<User> friends = new HashSet<>();

    public User(){}

    public User(String name, char sex, LocalDate dateOfBirth, Set<User> friends) {
        this.dob = dateOfBirth;
        this.name = name;
        this.sex = sex;
        this.friends = friends;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dateOfBirth) {
        this.dob = dateOfBirth;
    }

    public Set<User> getFriends(){
        return this.friends;
    }

    public void setFriends(Set<User> friends){
        this.friends = friends;
    }
}
