package com.izaias.valentim.userms.controllers;

import com.izaias.valentim.userms.models.DTO.UserDTO;
import com.izaias.valentim.userms.models.DTO.UsernameResponseFeign;
import com.izaias.valentim.userms.models.User;
import com.izaias.valentim.userms.models.modelsToRequests.Usernames;
import com.izaias.valentim.userms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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

    @PostMapping(value = "verify/")
    public ResponseEntity<Set<UsernameResponseFeign>> verifyListOfUsernames(@RequestBody Usernames usernames) {
        return ResponseEntity.ok().body(userService.verifyIfUsersExistsByUsernames(usernames.getUsernames()));
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> createUser(@RequestBody User userToAdd) {
        return userService.createUser(userToAdd);
    }

    @PatchMapping(value = "/{username}/{rolename}/")
    public ResponseEntity<UserDTO> addNewRoleUser(@PathVariable String username, @PathVariable String rolename) {
        return userService.addNewRoleToUser(username, rolename);
    }

    @DeleteMapping(value = "/{username}/")
    public ResponseEntity<?> deleteUser(@PathVariable String username) {
        if (userService.deleteUser(username))
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
}
