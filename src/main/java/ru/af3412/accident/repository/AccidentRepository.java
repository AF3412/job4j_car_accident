package ru.af3412.accident.repository;

import ru.af3412.accident.model.Accident;

import java.util.Map;

public interface AccidentRepository {

    Map<Integer, Accident> findAllAccidents();
    Accident addAccident(Accident accident);
    Accident findAccidentById(int id);
    Accident updateAccident(Accident accident);
    void deleteAccident(Accident accident);

}
