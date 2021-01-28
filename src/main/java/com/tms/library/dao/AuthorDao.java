package com.tms.library.dao;

import com.tms.library.model.Author;

import java.sql.SQLException;
import java.util.List;

public interface AuthorDao {

    Author findById(Integer id) throws SQLException;

    List<Author> findAll() throws SQLException;

    Author create(Author genre);

    Author update(Author genre);

    void delete(Integer id);

}