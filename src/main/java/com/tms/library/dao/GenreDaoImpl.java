package com.tms.library.dao;

import com.tms.library.exceptions.MyServiceException;
import com.tms.library.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class GenreDaoImpl implements GenreDao {

    @Autowired
    private DataSource dataSource;

    private final Logger LOG = Logger.getLogger(GenreDaoImpl.class.getName());

    public Genre findById(Integer id) throws SQLException {
        LOG.log(Level.INFO, "inside findById()");
        Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement("select  * from genre where id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        List<Genre> genres = createGenre(rs);
        LOG.log(Level.INFO, "findById() return: " + genres);
        conn.close();
        return genres.isEmpty() ? null : genres.get(0);
    }

    public List<Genre> findAll() throws SQLException {
        LOG.log(Level.INFO, "inside findAll()");
        Connection conn = dataSource.getConnection();
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("select  * from genre");
        LOG.log(Level.INFO, "findById() return all genres");
        List<Genre> genres = createGenre(rs);
        conn.close();
        return genres;
    }

    public int create(Genre genre) throws SQLException {
        LOG.log(Level.INFO, "inside create()");
        int idCreated = -1;
        Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement("insert into genre (genre) values (?)", Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, genre.getName());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            idCreated = rs.getInt(1);
        }
        if(idCreated == -1) {
            try {
                throw new MyServiceException("genre doesn't saved");
            } catch (MyServiceException e) {
                e.printStackTrace();
            }
        }
        LOG.log(Level.INFO, "create() return genre id: " + idCreated);
        conn.close();
        return idCreated;
    }

    public Genre update(Genre genre) throws SQLException {
        LOG.log(Level.INFO, "inside update()");
        int idCreated = -1;
        Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement("UPDATE genre SET genre = ? WHERE id = ?");
        ps.setString(1, genre.getName());
        ps.setInt(2, genre.getId());
        ps.executeUpdate();
        LOG.log(Level.INFO, "update() return genre id: " + idCreated);
        conn.close();
        return genre;
    }

    public void delete(Integer id) throws SQLException {
        LOG.log(Level.INFO, "inside delete()");
        Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement("delete from genre where id = ?");
        ps.setInt(1, id);
        ps.executeUpdate();
        LOG.log(Level.INFO, "delete() removed genre with ID:" + id);
        conn.close();
    }

    private List<Genre> createGenre(ResultSet rs) throws SQLException {
        List<Genre> genres = new ArrayList<Genre>();
        while (rs.next()) {
            Genre g = new Genre();
            g.setId(rs.getInt("id"));
            g.setName(rs.getString("genre"));

            genres.add(g);
        }
        return genres;
    }
}