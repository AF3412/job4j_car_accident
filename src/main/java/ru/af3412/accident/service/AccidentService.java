package ru.af3412.accident.service;

import org.springframework.stereotype.Service;
import ru.af3412.accident.model.Accident;
import ru.af3412.accident.repository.AccidentRepository;

import java.util.Collection;

@Service
public class AccidentService {

    private final AccidentRepository accidentRepository;

    public AccidentService(AccidentRepository accidentRepository) {
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

}
