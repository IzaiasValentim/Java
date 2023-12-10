package com.izaias.valentim.userms.controllers;

import com.izaias.valentim.userms.models.DTO.UserDTO;
import com.izaias.valentim.userms.models.User;
import com.izaias.valentim.userms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return userService.getAllUser();
    }

    @GetMapping(value = "search")
    public ResponseEntity<UserDTO> getUserByUsername(@RequestParam String username) {
        return userService.findUserByUsername(username);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User userToAdd) {
        return userService.createUser(userToAdd);
    }


}
