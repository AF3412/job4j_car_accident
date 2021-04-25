package ru.af3412.accident.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.af3412.accident.model.Accident;
import ru.af3412.accident.model.AccidentType;
import ru.af3412.accident.model.Rule;
import ru.af3412.accident.repository.AccidentRepository;

import java.util.Collection;

@Service
public class AccidentService {

    private final AccidentRepository accidentRepository;

    public AccidentService(
                    @Qualifier("accidentJdbcTemplate")
                    AccidentRepository accidentRepository) {
        this.accidentRepository = accidentRepository;
    }


    public Collection<Accident> findAllAccidents() {
        return accidentRepository.findAllAccidents();
    }

    public Accident create(Accident accident) {
        if (accident.getId() != 0) {
            return accidentRepository.updateAccident(accident);
        } else {
            return accidentRepository.create(accident);
        }
    }

    public Accident findAccidentById(int id) {
        return accidentRepository.findAccidentById(id);
    }

    public Collection<AccidentType> findAllAccidentTypes() {
        return accidentRepository.findAllAccidentTypes();
    }

    public Collection<Rule> findAllRules() {
        return accidentRepository.findAllRules();
    }

}
