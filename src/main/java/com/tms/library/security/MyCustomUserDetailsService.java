package com.tms.library.security;

import com.tms.library.dao.UserDao;
import com.tms.library.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class MyCustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User found = userDao.findByUsername(username);

        if (found == null) {
            throw new UsernameNotFoundException("Provided username is incorrect");
        }

        return new MyCustomUserDetails(
                found.getUsername(),
                found.getPassword(),
                Arrays.asList("ADMIN", "USER"),
                found.getFirstName(),
                found.isActive(),
                found.getCreated(),
                found.getUpdated());
    }
}
