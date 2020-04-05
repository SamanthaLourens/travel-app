package com.saml.travellersapp.controller;


import com.saml.travellersapp.model.User;
import com.saml.travellersapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/")
    public String index() {
        return "Welcome!";
    }

    @GetMapping("/show-all-users")
    public List<User> showAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("{userId}/show-friends")
    public Set<User> getFriends(@PathVariable int userId){
        return userRepository.findById(userId).getFriends();
    }

    @GetMapping("/find-user/{userName}")
    public List<User> findUserByName(@PathVariable String userName) {
        return userRepository.findByName(userName);
    }

    @PostMapping("update-user/{id}/{newName}")
    public User updateUser(@PathVariable int id, @PathVariable String newName) {
        User toUpdate = userRepository.findById(id);
        toUpdate.setName(newName);
        userRepository.save(toUpdate);
        return toUpdate;
    }

    @PostMapping("{userId}/add-friend/{friendId}")
    public void addFriend(@PathVariable int userId, @PathVariable int friendId) {
        User one = userRepository.findById(userId);
        User two = userRepository.findById(friendId);
        addFriendToFriendSet(one, two);
        addFriendToFriendSet(two, one);
    }

    @DeleteMapping("{userId}/delete-user")
    public void deleteUser(@PathVariable int userId){
        User toDelete = userRepository.findById(userId);
        userRepository.delete(toDelete);
    }

    @PostMapping("/create-user/{name}/{sex}/{dob}")
    public User createUser(@PathVariable String name, @PathVariable char sex, @PathVariable String dob) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
        LocalDate dateOfbirth = LocalDate.parse(dob, formatter);
        User newUser = new User(name, sex, dateOfbirth, new HashSet<>());
        userRepository.save(newUser);
        return newUser;
    }

    private void addFriendToFriendSet(User one, User two) {
        Set<User> friendsOne = one.getFriends();
        friendsOne.add(two);
        one.setFriends(friendsOne);
        userRepository.save(one);
    }


}
