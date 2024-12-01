package com.gaos.gaos.domain.dao;

import com.gaos.gaos.persistence.entity.Auth;
import com.gaos.gaos.persistence.entity.Person;
import com.gaos.gaos.persistence.entity.Role;

public class UsersDTO {
    private Person person;
    private Auth auth;
    private Role role;

    public UsersDTO() {
    }

    public UsersDTO(Person person, Auth auth, Role role) {
        this.person = person;
        this.auth = auth;
        this.role = role;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
