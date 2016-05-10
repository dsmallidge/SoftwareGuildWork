/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.dvd.dao;

import com.swcguild.dvd.model.Dvd;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class DvdListDBDao implements DvdListDao {

    private static final String SQL_INSERT_DVD
            = "insert into dvds (title, release_date, mpaa_rating, director, studio, note) values (?, ?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_DVD
            = "delete from dvds where id = ?";

    private static final String SQL_SELECT_DVD
            = "select * from dvds where id = ?";

    private static final String SQL_UPDATE_DVD
            = "update dvds set title = ?, release_date = ?, mpaa_rating = ?, director = ?, studio = ?, note = ? where id = ?";

    private static final String SQL_SELECT_ALL_DVDS
            = "select * from dvds";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Dvd addDvd(Dvd dvd) {
        jdbcTemplate.update(SQL_INSERT_DVD,
                dvd.getTitle(),
                dvd.getReleaseDate(),
                dvd.getMpaaRating(),
                dvd.getDirector(),
                dvd.getStudio(),
                dvd.getNote());
        dvd.setId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
        return dvd;
    }

    public void removeDvd(int dvdId) {
        jdbcTemplate.update(SQL_DELETE_DVD, dvdId);
    }

    public void updateDvd(Dvd dvd) {
        jdbcTemplate.update(SQL_UPDATE_DVD,
                dvd.getTitle(),
                dvd.getReleaseDate(),
                dvd.getMpaaRating(),
                dvd.getDirector(),
                dvd.getStudio(),
                dvd.getNote(),
                dvd.getId());
    }

    public List<Dvd> getAllDvds() {
        return jdbcTemplate.query(SQL_SELECT_ALL_DVDS, new DVDMapper());
    }

    public Dvd getDvdById(int dvdId) {

        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_DVD, new DVDMapper(), dvdId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Dvd> searchDvds(Map<SearchTerm, String> criteria) {
        if (criteria.size() == 0) {
            return getAllDvds();
        } else {
            StringBuilder sQuery = new StringBuilder("select * from dvds where ");

            int numParams = criteria.size();
            int paramPosition = 0;
            String[] paramVals = new String[numParams];

            Set<SearchTerm> keySet = criteria.keySet();
            Iterator<SearchTerm> iter = keySet.iterator();
            while (iter.hasNext()) {
                SearchTerm currentKey = iter.next();
                if (paramPosition > 0) {
                    sQuery.append(" and ");
                }

                sQuery.append(currentKey);
                sQuery.append(" = ? ");

                paramVals[paramPosition] = criteria.get(currentKey);
                paramPosition++;
            }
            List<Dvd> result = jdbcTemplate.query(sQuery.toString(), new DVDMapper(), paramVals);
            return result;
        }

    }

    private static final class DVDMapper implements RowMapper<Dvd> {

        public Dvd mapRow(ResultSet rs, int rowNum) throws SQLException {
            Dvd dvd = new Dvd();
            dvd.setTitle(rs.getString("title"));
            dvd.setReleaseDate(rs.getString("release_date"));
            dvd.setMpaaRating(rs.getString("mpaa_rating"));
            dvd.setDirector(rs.getString("director"));
            dvd.setStudio(rs.getString("studio"));
            dvd.setNote(rs.getString("note"));
            dvd.setId(rs.getInt("id"));
            return dvd;
        }
    }
    
    public enum SearchTerm {
    TITLE, RELEASE_DATE, MPAA_RATING, DIRECTOR, STUDIO, NOTE
}

}
