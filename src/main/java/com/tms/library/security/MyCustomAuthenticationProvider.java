package com.tms.library.security;

import com.tms.library.dao.UserDao;
import com.tms.library.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class MyCustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        String username = auth.getName();
        String password = auth.getCredentials().toString();

        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            throw new BadCredentialsException("Username and password should be not empty");
        }

        User found = userDao.findByUsername(username);

        if (!passwordEncoder.matches(password, found.getPassword())) {
            throw new BadCredentialsException("Password invalid");
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(found, null, Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER")));
        return authentication;
    }

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }
}
