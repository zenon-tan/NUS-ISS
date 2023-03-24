package day22.workshop.repo;

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
            select * from rsvp;
            """;

    private static final String SQL_GET_RSVP_BY_NAME = """
            select * from rsvp where name like ?
            """;

    private static final String SQL_GET_RSVP_BY_ID = """
            select * from rsvp where id = ?
            """;

    private static final String SQL_ADD_RSVP = """
            insert into rsvp (id, name, email, phone, confirmation_date, comments)
            values(?, ?, ?, ?, ?, ?)
            """;

    private static final String SQL_UPDATE_RSVP_BY_ID = """
            update rsvp set 
            name = ?, email = ?, phone = ?, confirmation_date = ?, comments = ?
            where id = ?
            """;

    private static final String SQL_COUNT_RSVP = """
            select count(*) from rsvp
            """;

    public List<Rsvp> getAllRsvp() {

        return jdbcTemplate.query(SQL_GET_ALL_RSVP, new BeanPropertyRowMapper().newInstance(Rsvp.class));
        
    }

    public List<Rsvp> getRsvpByName(String name) {

        return jdbcTemplate.query(SQL_GET_RSVP_BY_NAME, new BeanPropertyRowMapper().newInstance(Rsvp.class),
         "%" + name + "%");

    }

    public Optional<Rsvp> getRsvpById(String id) {

        try {

            Rsvp result = jdbcTemplate.queryForObject(SQL_GET_RSVP_BY_ID, new BeanPropertyRowMapper().newInstance(Rsvp.class),
            id);
            return Optional.of(result);
            
        } catch (DataAccessException e) {
            return Optional.empty();
        }

    }

    public Boolean addRsvp(Rsvp rsvp) {

        Rsvp rsvpid = new Rsvp();
        return jdbcTemplate.update(SQL_ADD_RSVP, rsvpid.getId(), rsvp.getName(), rsvp.getEmail(),
        rsvp.getPhone(), rsvp.getConfirmationDate(), rsvp.getComments()) > 0;

    }

    public Boolean updateRsvp(Rsvp rsvp) {

        return jdbcTemplate.update(SQL_UPDATE_RSVP_BY_ID, rsvp.getName(), rsvp.getEmail(), rsvp.getPhone(),
        rsvp.getConfirmationDate(), rsvp.getComments(), rsvp.getId()) > 0;
    }

    public int countRsvp() {

        int count = jdbcTemplate.queryForObject(SQL_COUNT_RSVP, Integer.class);
        return count;
    }
    
}
