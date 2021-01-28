package com.tms.library.security;

import lombok.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Value
public class MyCustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private List<String> roles;
    private String firstName;
    private boolean active;
    private Date created;
    private Date updated;

    public MyCustomUserDetails(String username, String password, List<String> roles, String firstName, boolean active, Date created, Date updated) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.firstName = firstName;
        this.active = active;
        this.created = created;
        this.updated = updated;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(it -> new SimpleGrantedAuthority("ROLE_" + it)).collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
//        LocalDateTime now = LocalDateTime.now();
//        LocalDateTime updated = LocalDateTime.of(
//                this.updated.getYear(),
//                this.updated.getMonth(),
//                this.updated.getDay(),
//                this.updated.getHours(),
//                this.updated.getMinutes());
//        return updated.plusDays(20).isBefore(now);
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
