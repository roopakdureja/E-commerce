package com.ecommerce.project.security.payload;

import com.ecommerce.project.model.Role;

import java.util.List;
import java.util.Set;

public class UserInfoResponse {

    private Integer id;
    private String jwtToken;
    private String userName;
    private Set<String> roles;

    public UserInfoResponse(Integer id, String jwtToken, String userName, Set<String> roles) {
        this.id = id;
        this.jwtToken = jwtToken;
        this.userName = userName;
        this.roles = roles;
    }

    public UserInfoResponse(Integer id, String userName, Set<String> roles) {
        this.id = id;
        this.userName = userName;
        this.roles = roles;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
