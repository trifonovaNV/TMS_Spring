package com.tms.library.service;

import com.tms.library.exceptions.MyServiceException;
import com.tms.library.model.Author;

import java.util.List;

public interface AuthorService {

    Author getById(Integer id) throws MyServiceException;

    List<Author> getAll() throws MyServiceException;

    Author create(Author Author);

    Author update(Author Author);

    void delete(Integer id);
}