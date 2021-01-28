package com.tms.library.service;

import com.tms.library.dao.AuthorDao;
import com.tms.library.exceptions.MyServiceException;
import com.tms.library.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final Logger LOG = Logger.getLogger(AuthorServiceImpl.class.getName());
    private final AuthorDao AuthorDao;

    @Autowired
    public AuthorServiceImpl(AuthorDao AuthorDao) {
        this.AuthorDao = AuthorDao;
    }

    public Author getById(Integer id) throws MyServiceException {
        LOG.log(Level.INFO, "getById(" + id + ") method called");
        try {
            return AuthorDao.findById(id);
        } catch (SQLException e) {
            LOG.log(Level.WARNING, "Exception appeared while executing 'getById' with parameter id = " + id);
            throw new MyServiceException(e.getLocalizedMessage());
        }
    }

    public List<Author> getAll() throws MyServiceException {
        LOG.log(Level.INFO, "getAll method called");
        try {
            return AuthorDao.findAll();
        } catch (SQLException e) {
            LOG.log(Level.WARNING, "Exception appeared while executing 'getAll' method");
            throw new MyServiceException(e.getLocalizedMessage());
        }
    }

    public Author create(Author Author) {
        return null;
    }

    public Author update(Author Author) {
        return null;
    }

    public void delete(Integer id) {

    }
}
