package com.izaias.valentim.userms.services;

import com.izaias.valentim.userms.models.DTO.UserDTO;
import com.izaias.valentim.userms.models.Role;
import com.izaias.valentim.userms.models.User;
import com.izaias.valentim.userms.repositories.RoleRepository;
import com.izaias.valentim.userms.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository,
                       BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public ResponseEntity<?> createUser(User userToCreate) {
        Role add = getAllRoles().get(0);
        userToCreate.setRoles(add);
        String pass = userToCreate.getPassword();
        System.out.println(pass);
        String passWordEncript = passwordEncoder.encode(pass);
        userToCreate.setPassword(passWordEncript);

        userRepository.save(userToCreate);
        URI readerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(userToCreate.getId())
                .toUri();
        return ResponseEntity.created(readerLocation).build();
    }

    public ResponseEntity<UserDTO> findUserByUsername(String username) {
        User searchUser = userRepository.findUserByUsername(username);
        if (searchUser != null) {
            UserDTO userToReturn = new UserDTO(searchUser);
            return ResponseEntity.ok().body(userToReturn);
        }
        return ResponseEntity.notFound().build();

    }

    public ResponseEntity<List<UserDTO>> getAllUser() {
        List<User> allUsers = userRepository.findAll();
        List<UserDTO> allUsersDTO = new ArrayList<>();

        allUsers.forEach(user -> {
            UserDTO dto = new UserDTO(user);
            allUsersDTO.add(dto);
        });
        return ResponseEntity.ok().body(allUsersDTO);
    }


}
