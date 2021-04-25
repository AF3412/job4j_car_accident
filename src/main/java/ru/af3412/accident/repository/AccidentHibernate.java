package ru.af3412.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.af3412.accident.model.Accident;
import ru.af3412.accident.model.AccidentType;
import ru.af3412.accident.model.Rule;

import java.util.Collection;

@Repository
public class AccidentHibernate implements AccidentRepository {

    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public Collection<Accident> findAllAccidents() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from Accident", Accident.class)
                    .list();
        }
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
        try (Session session = sf.openSession()) {
            session.save(accident);
            return accident;
        }
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
