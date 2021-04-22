package ru.af3412.accident.repository;

import ru.af3412.accident.model.Accident;
import ru.af3412.accident.model.AccidentType;

import java.util.Collection;

public interface AccidentRepository {

    Collection<Accident> findAllAccidents();

    Collection<AccidentType> findAllAccidentTypes();

    Accident create(Accident accident);
    Accident findAccidentById(int id);
    Accident updateAccident(Accident accident);
    void deleteAccident(Accident accident);

}
