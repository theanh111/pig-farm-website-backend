package com.school.core.entity;

import lombok.Data;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class UserPrinciple implements UserDetails, CredentialsContainer {
    private Integer id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> roles;
    private String status;
    private Integer parentId;
    private Integer roleId;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public UserPrinciple(Integer id, String username, String password,
                         Collection<? extends GrantedAuthority> roles, String status, Integer parentId, Integer roleId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.status = status;
        this.parentId = parentId;
        this.roleId = roleId;
    }
    public UserPrinciple(){}

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        if(this.status.equals("I")){
            return false;
        }
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void eraseCredentials() {
        this.password = null;
    }

    public boolean isChild() {
        if(this.parentId==0){
            return false;
        }
        return true;
    }
}
