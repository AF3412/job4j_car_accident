package ru.af3412.accident.service;

import org.springframework.stereotype.Service;
import ru.af3412.accident.model.Accident;
import ru.af3412.accident.repository.AccidentRepository;

import java.util.Map;

@Service
public class AccidentService {

    private final AccidentRepository accidentRepository;

    public AccidentService(AccidentRepository accidentRepository) {
        this.accidentRepository = accidentRepository;
    }

    public Map<Integer, Accident> findAllAccidents() {
        return accidentRepository.findAllAccidents();
    }

}
