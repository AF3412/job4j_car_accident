package ru.af3412.accident.repository;

import org.springframework.stereotype.Repository;
import ru.af3412.accident.model.Accident;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccidentMem implements AccidentRepository {

    private final ConcurrentHashMap<Integer, Accident> accidents;

    public AccidentMem() {
        this.accidents = new ConcurrentHashMap<>();
        this.accidents.put(1, new Accident(1, "accident 1", "text 1", "addr 1"));
        this.accidents.put(2, new Accident(2, "accident 2", "text 2", "addr 2"));
        this.accidents.put(3, new Accident(3, "accident 3", "text 3", "addr 3"));
    }

    @Override
    public Collection<Accident> findAllAccidents() {
        return accidents.values();
    }

    @Override
    public Accident addAccident(Accident accident) {
        return null;
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
