package com.tms.library.dao;

import com.tms.library.model.Genre;

import java.sql.SQLException;
import java.util.List;

public interface GenreDao {

    Genre findById(Integer id) throws SQLException;

    List<Genre> findAll() throws SQLException;

    int create(Genre genre) throws SQLException;

    Genre update(Genre genre) throws SQLException;

    void delete(Integer id) throws SQLException;

}