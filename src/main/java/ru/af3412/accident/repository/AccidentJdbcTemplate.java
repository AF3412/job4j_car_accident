package ru.af3412.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.af3412.accident.model.Accident;
import ru.af3412.accident.model.AccidentType;
import ru.af3412.accident.model.Rule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AccidentJdbcTemplate implements AccidentRepository {

    private final JdbcTemplate jdbc;
    private final NamedParameterJdbcTemplate namedJdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc, NamedParameterJdbcTemplate namedJdbc) {
        this.jdbc = jdbc;
        this.namedJdbc = namedJdbc;
    }

    @Override
    public Collection<Accident> findAllAccidents() {
        return jdbc.query("SELECT a.id acc_id, a.name acc_name, a.text acc_text, a.address acc_addr, t.id type_id, t.name type_name " +
                        "FROM accident a " +
                        "JOIN accident_types t ON t.id = a.accident_type_id",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("acc_id"));
                    accident.setName(rs.getString("acc_name"));
                    accident.setText(rs.getString("acc_text"));
                    accident.setAddress(rs.getString("acc_addr"));
                    accident.setType(AccidentType.of(
                            rs.getInt("type_id"),
                            rs.getString("type_name")
                    ));
                    accident.setRules(new ArrayList<>(findRulesByAccidentId(rs.getInt("acc_id"))));
                    return accident;
                });
    }

    @Override
    public Collection<AccidentType> findAllAccidentTypes() {
        return jdbc.query("SELECT id, name FROM accident_types",
                (rs, row) -> AccidentType.of(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
    }

    @Override
    public Collection<Rule> findAllRules() {
        return jdbc.query("SELECT id, name FROM rules",
                (rs, row) -> Rule.of(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
    }

    @Override
    @Transactional
    public Accident create(Accident accident) {
        MapSqlParameterSource parameterSource = getMapSqlParameterForAccident(accident);
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        namedJdbc.update("INSERT INTO accident (name, text, address, accident_type_id) " +
                        "VALUES (:name, :text, :address, :accident_type_id)",
                parameterSource,
                keyHolder,
                new String[]{"id"}
        );
        int accidentId = keyHolder.getKey().intValue();
        accident.setId(accidentId);
        saveRuleForAccident(accident);
        return accident;
    }

    @Override
    public Accident findAccidentById(int id) {
        Accident result = jdbc.queryForObject("SELECT id, name, text, address FROM accident WHERE id = ?",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    return accident;
                }, id);
        result.setRules(new ArrayList<>(findRulesByAccidentId(id)));
        return result;
    }

    @Override
    @Transactional
    public Accident updateAccident(Accident accident) {
        MapSqlParameterSource parameterSource = getMapSqlParameterForAccident(accident);
        parameterSource.addValue("id", accident.getId());
        namedJdbc.update(
                "UPDATE accident " +
                "SET name = :name, text = :text, address = :address, accident_type_id = :accident_type_id " +
                "WHERE id = :id",
                parameterSource);
        saveRuleForAccident(accident);
        return findAccidentById(accident.getId());
    }

    @Override
    public void deleteAccident(Accident accident) {
        deleteRowsFromAccidenAndRuleByAcciden(accident);
        jdbc.update(
                "DELETE FROM accident WHERE id = ?",
                accident.getId()
        );
    }

    private Collection<Rule> findRulesByAccidentId(int id) {
        return jdbc.query("SELECT r.id, r.name FROM rules r " +
                        "JOIN accident_and_rule aar ON r.id = aar.rule_id AND aar.accident_id = ?",
                (rs, row) -> Rule.of(
                        rs.getInt("id"),
                        rs.getString("name")
                ),
                id);
    }

    private void saveRuleForAccident(Accident accident) {
        deleteRowsFromAccidenAndRuleByAcciden(accident);
        List<MapSqlParameterSource> params = accident.getRules().stream()
                .map(rule -> new MapSqlParameterSource()
                        .addValue("accident_id", accident.getId())
                        .addValue("rule_id", rule.getId()))
                .collect(Collectors.toList());
        namedJdbc.batchUpdate(
                "INSERT INTO accident_and_rule (accident_id, rule_id) VALUES (:accident_id, :rule_id)",
                params.toArray(MapSqlParameterSource[]::new)
        );
    }

    private void deleteRowsFromAccidenAndRuleByAcciden(Accident accident) {
        jdbc.update("DELETE FROM accident_and_rule WHERE accident_id = ?", accident.getId());
    }

    private MapSqlParameterSource getMapSqlParameterForAccident(Accident accident) {
        return new MapSqlParameterSource()
                .addValue("name", accident.getName())
                .addValue("text", accident.getText())
                .addValue("address", accident.getAddress())
                .addValue("accident_type_id", accident.getType().getId());
    }
}
