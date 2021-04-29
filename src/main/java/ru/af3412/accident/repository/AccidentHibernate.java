package ru.af3412.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.af3412.accident.model.Accident;
import ru.af3412.accident.model.AccidentType;
import ru.af3412.accident.model.Rule;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;

@Repository
public class AccidentHibernate implements AccidentRepository {

    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public Collection<Accident> findAllAccidents() {
        return this.tx(session -> session.createQuery("from Accident", Accident.class).list());
    }

    @Override
    public Collection<AccidentType> findAllAccidentTypes() {
        return this.tx(session -> session.createQuery("from AccidentType", AccidentType.class).list());
    }

    @Override
    public Collection<Rule> findAllRules() {
        return this.tx(session -> session.createQuery("from Rule", Rule.class).list());
    }

    @Override
    public Accident create(Accident accident) {
        this.tx(session -> session.save(accident));
        return accident;
    }

    @Override
    public Accident findAccidentById(int id) {
        return this.tx(session -> session.get(Accident.class, id));
    }

    @Override
    public Accident updateAccident(Accident accident) {
        this.vx(session -> session.update(accident));
        return findAccidentById(accident.getId());
    }

    @Override
    public void deleteAccident(Accident accident) {
        this.vx(session -> session.delete(accident));
    }

    private <T> T tx(final Function<Session, T> command) {
        try (Session session = sf.openSession()) {
            try {
                Transaction tx = session.beginTransaction();
                T rsl = command.apply(session);
                tx.commit();
                return rsl;
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw e;
            }
        }
    }

    private void vx(final Consumer<Session> command) {
        try (Session session = sf.openSession()) {
            try {
                Transaction tx = session.beginTransaction();
                command.accept(session);
                tx.commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw e;
            }
        }
    }
}
