package ru.af3412.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.af3412.accident.model.Accident;
import ru.af3412.accident.model.AccidentType;
import ru.af3412.accident.model.Rule;

import java.util.Collection;

@Repository
public class AccidentJdbcTemplate implements AccidentRepository {

    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Collection<Accident> findAllAccidents() {
        return jdbc.query("SELECT id, name FROM accident",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    return accident;
                });
    }

    @Override
    public Collection<AccidentType> findAllAccidentTypes() {
        return null;
    }

    @Override
    public Collection<Rule> findAllRules() {
        return null;
    }

    @Override
    public Accident create(Accident accident) {
        jdbc.update("insert into accident (name) values (?)",
                accident.getName());
        return accident;
    }

    @Override
    public Accident findAccidentById(int id) {
        return null;
    }

    @Override
    public Accident updateAccident(Accident accident) {
        return null;
    }

    @Override
    public void deleteAccident(Accident accident) {

    }
}
