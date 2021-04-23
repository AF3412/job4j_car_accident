package ru.af3412.accident.repository;

import ru.af3412.accident.model.Accident;
import ru.af3412.accident.model.AccidentType;
import ru.af3412.accident.model.Rule;

import java.util.Collection;

public interface AccidentRepository {

    Collection<Accident> findAllAccidents();

    Collection<AccidentType> findAllAccidentTypes();

    Collection<Rule> findAllRules();

    Accident create(Accident accident);
    Accident findAccidentById(int id);
    Accident updateAccident(Accident accident);
    void deleteAccident(Accident accident);

}
