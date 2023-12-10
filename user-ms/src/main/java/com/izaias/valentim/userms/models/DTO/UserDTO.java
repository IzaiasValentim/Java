package com.izaias.valentim.userms.models.DTO;

import com.izaias.valentim.userms.models.Role;
import com.izaias.valentim.userms.models.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

public class UserDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private long id;
    private String fullName;

    private String username;
    private Set<Role> roles;

    public UserDTO(User usuerToDTO) {
        this.id = usuerToDTO.getId();
        this.fullName = usuerToDTO.getFullName();
        this.username = usuerToDTO.getUsername();
        this.roles = usuerToDTO.getRoles();
    }

    public UserDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
