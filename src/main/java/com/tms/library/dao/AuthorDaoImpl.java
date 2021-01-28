package com.tms.library.dao;


import com.tms.library.model.Author;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthorDaoImpl implements AuthorDao {

    private final String URL = "jdbc:mysql://localhost:3306/library";
//    private final String USER = "root";
//    private final String PASS = "root";

//    private String USER = "root";
//    private String PASS = "root";

    private String USER;
    private String PASS;

    @PostConstruct
    public void setUp() {
        System.out.println(this.getClass() + " is creating");
        USER = "root";
        PASS = "root";
    }

    @PreDestroy
    public void tierDown() {
        System.out.println(this.getClass() + " in destroying process");
    }

    public String getUSER() {
        return USER;
    }

    public void setUSER(String USER) {
        this.USER = USER;
    }

    public String getPASS() {
        return PASS;
    }

    public void setPASS(String PASS) {
        this.PASS = PASS;
    }

    public Author findById(Integer id) throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASS);
        PreparedStatement ps = conn.prepareStatement("select  * from author where id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        List<Author> Authors = createAuthor(rs);
        conn.close();
        return Authors.isEmpty() ? null : Authors.get(0);
    }

    public List<Author> findAll() throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASS);
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("select  * from author");
        List<Author> Authors = createAuthor(rs);
        conn.close();
        return Authors;
    }

    public Author create(Author Author) {
        throw new UnsupportedOperationException("ПОка не запилили");
    }

    public Author update(Author Author) {
        throw new UnsupportedOperationException("ПОка не запилили");
    }

    public void delete(Integer id) {
        throw new UnsupportedOperationException("ПОка не запилили");
    }

    private List<Author> createAuthor(ResultSet rs) throws SQLException {
        List<Author> Authors = new ArrayList<Author>();
        while (rs.next()) {
            Author g = new Author();
            g.setId(rs.getInt("id"));
            g.setName(rs.getString("name"));

            Authors.add(g);
        }
        return Authors;
    }
}