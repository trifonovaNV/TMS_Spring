package com.tms.library.service;

import com.tms.library.dao.GenreDao;
import com.tms.library.exceptions.MyServiceException;
import com.tms.library.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class GenreServiceImpl implements GenreService {

    private final Logger LOG = Logger.getLogger(GenreServiceImpl.class.getName());
    private final GenreDao genreDao;

    @Autowired
    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    public Genre getById(Integer id) throws MyServiceException {
        LOG.log(Level.INFO, "getById(" + id + ") method called");
        try {
            return genreDao.findById(id);
        } catch (SQLException e) {
            LOG.log(Level.WARNING, "Exception appeared while executing 'getById' with parameter id = " + id);
            throw new MyServiceException(e.getLocalizedMessage());
        }
    }

    public List<Genre> getAll() throws MyServiceException {
        LOG.log(Level.INFO, "getAll method called");
        try {
            return genreDao.findAll();
        } catch (SQLException e) {
            LOG.log(Level.WARNING, "Exception appeared while executing 'getAll' method");
            throw new MyServiceException(e.getLocalizedMessage());
        }
    }

    public Genre create(Genre genre) {
        LOG.log(Level.INFO, "create method called");
        try {
            int id = genreDao.create(genre);
            genre.setId(id);
            LOG.log(Level.INFO, "create method created genre with name: " + genre.getName());
            return genre;
        } catch (SQLException e) {
            LOG.log(Level.WARNING, "Exception appeared while executing 'create' method");
            throw new MyServiceException(e.getLocalizedMessage());
        }
    }

    public Genre update(Genre genre) throws SQLException {
        LOG.log(Level.INFO, "update method called");
        Genre updated = genreDao.update(genre);
        LOG.log(Level.INFO, "update method created genre with name: " + genre.getName());
        return updated;
    }

    public void delete(Integer id) throws SQLException {
        LOG.log(Level.INFO, "delete method called");
        genreDao.delete(id);
        LOG.log(Level.INFO, "delete method deleted genre with id: " + id);
    }
}
