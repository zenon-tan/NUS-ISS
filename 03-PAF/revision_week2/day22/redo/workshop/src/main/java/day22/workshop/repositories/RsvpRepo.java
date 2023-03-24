package day22.workshop.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import day22.workshop.models.Rsvp;

@Repository
public class RsvpRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SQL_GET_ALL_RSVP = """
            select * from rsvp
            """;

    private static final String SQL_GET_RSVP_BY_NAME = """
            select * from rsvp where name like ?
            """;

    private static final String SQL_GET_RSVP_BY_PHONE = """
            select * from rsvp where phone = ?
            """;

    private static final String SQL_ADD_RSVP = """
            insert into rsvp(name, email, phone, confirmation_date, comments)
            values(?, ?, ?, ?, ?)
            """;

    private static final String SQL_UPDATE_RSVP = """
        update rsvp set 
        name = ?,
        email = ?,
        confirmation_date = ?,
        comments = ?
        where phone = ?;
            """;

    public static final String SQL_COUNT_RSVP = """
            select count(*) from rsvp
            """;

    public Optional<List<Rsvp>> getAllRsvp() {

        try {

            List<Rsvp> result = jdbcTemplate.query(SQL_GET_ALL_RSVP, new BeanPropertyRowMapper().newInstance(Rsvp.class));
            return Optional.of(result);
            
        } catch (DataAccessException e) {
            return Optional.empty();
        }
        
    }

    public Optional<List<Rsvp>> getRsvpsByName(String name) {

        try {

            List<Rsvp> result = jdbcTemplate.query(SQL_GET_RSVP_BY_NAME, new BeanPropertyRowMapper().newInstance(Rsvp.class), "%" + name + "%");
            return Optional.of(result);
            
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    public Optional<Rsvp> checkRsvpByPhone(String phone) {
        try {
            Rsvp result = jdbcTemplate.queryForObject(SQL_GET_RSVP_BY_PHONE, new BeanPropertyRowMapper<>().newInstance(Rsvp.class), phone);
            return Optional.of(result);
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    public Boolean addRsvp(Rsvp rsvp) {
        return jdbcTemplate.update(SQL_ADD_RSVP, rsvp.getName(), rsvp.getEmail(), rsvp.getPhone(), rsvp.getConfirmationDate(), rsvp.getComments()) > 0;
    }

    public Boolean updateRsvp(Rsvp rsvp) {
        return jdbcTemplate.update(SQL_UPDATE_RSVP, rsvp.getName(), rsvp.getEmail(), rsvp.getConfirmationDate(), rsvp.getComments(), rsvp.getPhone()) > 0;
    }
    

    public String upsertRsvp(Rsvp rsvp) {

        Optional<Rsvp> query = checkRsvpByPhone(rsvp.getPhone());

        if(query.isPresent()) {
            updateRsvp(rsvp);
            return "Rsvp of the phone number %s already exists, Rsvp updated".formatted(rsvp.getPhone());
        }

        addRsvp(rsvp);
        return "Rsvp added";

    }

    public int countRsvps() {

        return jdbcTemplate.queryForObject(SQL_COUNT_RSVP, Integer.class);

    }
    
}
