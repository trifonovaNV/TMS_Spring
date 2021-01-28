package com.tms.library.service;

import com.tms.library.exceptions.MyServiceException;
import com.tms.library.model.Genre;

import java.sql.SQLException;
import java.util.List;

public interface GenreService {

    Genre getById(Integer id) throws MyServiceException;

    List<Genre> getAll() throws MyServiceException;

    Genre create(Genre genre);

    Genre update(Genre genre) throws SQLException;

    void delete(Integer id) throws SQLException;
}