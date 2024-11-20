package ru.kata.spring.boot_security.demo.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {
    private User username;
    private Role role;

    public UserDetailsImpl(User username, Role role) {
        this.username = username;
        this.role = role;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.toString());
        return List.of(authority);
    }

    @Override
    public String getPassword() {
        return this.username.getPassword();
    }

    @Override
    public String getUsername() {
        return this.username.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
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
}
