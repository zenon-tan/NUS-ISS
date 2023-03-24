package day22.workshop.repositories;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import day22.workshop.models.Rsvp;

@Repository
public class RsvpRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String GET_ALL_RSVP_SQL = """
        select * from rsvp
        """;

    private static final String GET_RSVP_BY_NAME_SQL = """
        select * from rsvp where name like ?
        """;

    private static final String ADD_RSVP_SQL = """
        insert into rsvp (id, name, email, phone, confirmation_date, response, comments)
        values(?, ?, ?, ?, ?, ?, ?);
            
            """;

    private static final String UPDATE_RSVP_SQL = """
        update rsvp set name = ?, email = ?, phone = ?, 
        confirmation_date = ?, response = ?, comments = ?
        where id = ?;
            """;

    public List<Rsvp> getAllRsvp() {

        return jdbcTemplate.query(GET_ALL_RSVP_SQL, new BeanPropertyRowMapper().newInstance(Rsvp.class));
        
    }

    public List<Rsvp> getRsvpByName(String name) {

        return jdbcTemplate.query(GET_RSVP_BY_NAME_SQL, 
        new BeanPropertyRowMapper().newInstance(Rsvp.class), "%" + name + "%");

    }

    public Boolean addRsvp(Rsvp rsvp) {

        return jdbcTemplate.update(ADD_RSVP_SQL, new Rsvp().getId(), rsvp.getName(), 
        rsvp.getEmail(), rsvp.getPhone(), rsvp.getConfirmationDate(), rsvp.getResponse(), rsvp.getComments()) > 0;
    }

    public Boolean updateRsvp(Rsvp rsvp) {

        return jdbcTemplate.update(UPDATE_RSVP_SQL, rsvp.getName(), 
        rsvp.getEmail(), rsvp.getPhone(), rsvp.getConfirmationDate(), rsvp.getResponse(), rsvp.getComments(), rsvp.getId()) > 0;
    }

    public int[] batchAddRsvp(final List<Rsvp> rsvps) {
        List<Object[]> params = rsvps.stream()
        .map(i -> new Object[] {new Rsvp().getId(), i.getName(), i.getEmail(), i.getPhone(), i.getConfirmationDate(), i.getResponse(), i.getComments()})
        .toList();

        return Arrays.stream(jdbcTemplate.batchUpdate(ADD_RSVP_SQL, params))
        .filter(i -> i == 1).toArray();
    }
    
}
