package com.izaias.valentim.taskms.models.feignEntities;

import com.izaias.valentim.taskms.models.Role;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class UsernameResponseFeign implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String username;
    private Set<Role> roles;

    public UsernameResponseFeign() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsernameResponseFeign that)) return false;
        return Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
