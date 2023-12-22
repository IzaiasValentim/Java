package com.izaias.valentim.userms.services;

import com.izaias.valentim.userms.models.DTO.UserDTO;
import com.izaias.valentim.userms.models.DTO.UsernameResponseFeign;
import com.izaias.valentim.userms.models.Role;
import com.izaias.valentim.userms.models.User;
import com.izaias.valentim.userms.repositories.RoleRepository;
import com.izaias.valentim.userms.repositories.UserRepository;
import com.izaias.valentim.userms.services.customExceptions.RoleNotFoundException;
import com.izaias.valentim.userms.services.customExceptions.UserAlreadyExistException;
import com.izaias.valentim.userms.services.customExceptions.UserNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public Role getEmployeeRole() {
        String roleName = "ROLE_EMPLOYEE";
        return roleRepository.findRoleByRoleName(roleName)
                .orElseThrow(() -> new RoleNotFoundException("Role not found"));
    }

    @Transactional
    public ResponseEntity<?> createUser(User userToCreate) {

        if (userRepository.findUserByUsername(userToCreate.getUsername()).isPresent()) {
            throw new UserAlreadyExistException("There is already a user with this username");
        }
        Role employeeRole = getEmployeeRole();
        userToCreate.setRoles(employeeRole);
        String pass = userToCreate.getPassword();
        System.out.println(pass);
        String passWordEncript = passwordEncoder.encode(pass);
        userToCreate.setPassword(passWordEncript);

        userRepository.save(userToCreate);
        URI readerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("id={id}")
                .buildAndExpand(userToCreate.getId())
                .toUri();
        return ResponseEntity.created(readerLocation).build();

    }

    @Transactional
    public ResponseEntity<UserDTO> addNewRoleToUser(String username, String roleName) {
        User searchUser = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with this username"));

        Role roleToAdd = roleRepository.findRoleByRoleName(roleName)
                .orElseThrow(() -> new RoleNotFoundException("Role not found"));

        searchUser.setRoles(roleToAdd);
        userRepository.save(searchUser);
        UserDTO userToReturn = new UserDTO(searchUser);
        return ResponseEntity.ok().body(userToReturn);
    }

    public ResponseEntity<UserDTO> findUserByUsername(String username) {
        User searchUser = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with this username"));

        UserDTO userToReturn = new UserDTO(searchUser);
        return ResponseEntity.ok().body(userToReturn);
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

    @Transactional
    public void deleteUser(String username) {
        User searchUser = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with this username"));

        userRepository.delete(searchUser);
    }

    public Set<UsernameResponseFeign> verifyIfUsersExistsByUsernames(List<String> usernamesToVerify) {
        Set<UsernameResponseFeign> listOfValidUsers = new HashSet<>();
        usernamesToVerify.forEach
                (username -> userRepository.findUserByUsername(username).ifPresent(
                        user -> listOfValidUsers.add(new UsernameResponseFeign(user))
                ));
        return listOfValidUsers;
    }
}
