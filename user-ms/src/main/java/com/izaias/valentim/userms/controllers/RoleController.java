package com.izaias.valentim.userms.controllers;

import com.izaias.valentim.userms.models.Role;
import com.izaias.valentim.userms.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "roles")
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok().body(roleService.getAllRoles());
    }

    @PostMapping(value = "{roleName}/")
    public ResponseEntity<?> createRole(@PathVariable String roleName) {
        return roleService.createNewRole(roleName);
    }

    @DeleteMapping(value = "{roleName}/")
    public ResponseEntity<?> deleteRole(@PathVariable String roleName) {
        if (roleService.deleteRole(roleName))
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }


}
