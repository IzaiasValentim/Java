package com.izaias.valentim.userms.controllers;

import com.izaias.valentim.userms.models.Role;
import com.izaias.valentim.userms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "roles")
public class RoleController {

    private final UserService userService;

    @Autowired
    public RoleController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping(value = "getAllRoles")
    public List<Role> getAllRoles(){
        return userService.getAllRoles();
    }

}
