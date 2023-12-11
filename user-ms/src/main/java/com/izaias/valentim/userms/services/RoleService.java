package com.izaias.valentim.userms.services;

import com.izaias.valentim.userms.models.Role;
import com.izaias.valentim.userms.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public ResponseEntity<?> createNewRole(String roleName) {
        Role objectRole = new Role();
        objectRole.setRoleName(roleName);
        roleRepository.save(objectRole);
        URI readerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("id={id}")
                .buildAndExpand(objectRole.getId())
                .toUri();
        return ResponseEntity.created(readerLocation).build();
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public boolean deleteRole(String roleName) {
        Role roleSearch = roleRepository.findRoleByRoleName(roleName).orElse(null);
        if (roleSearch != null) {
            roleRepository.delete(roleSearch);
            return true;
        }
        return false;
    }

}
