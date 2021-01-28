package com.tms.library.dao;

import com.tms.library.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findByUsername(String username) {
        List<User> users = jdbcTemplate.query(connection -> {
            PreparedStatement ps = connection.prepareStatement("SELECT id, username, password, first_name, last_name, active FROM users WHERE username = ?");
            ps.setString(1, username);
            return ps;
        }, new UserRowMapper());
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public User addUser(User user) {
        String encodedPass = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPass);
        return null;
    }

    private class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {
            return User.builder()
                    .id(rs.getLong("id"))
                    .username(rs.getString("username"))
                    .password(rs.getString("password"))
                    .firstName(rs.getString("first_name"))
                    .lastName(rs.getString("last_name"))
                    .active(rs.getBoolean("active"))
                    .build();
        }
    }
}
