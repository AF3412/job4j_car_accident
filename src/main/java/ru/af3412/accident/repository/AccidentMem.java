package ru.af3412.accident.repository;

import org.springframework.stereotype.Repository;
import ru.af3412.accident.model.Accident;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem implements AccidentRepository {

    private final ConcurrentHashMap<Integer, Accident> accidents;
    private final AtomicInteger count;

    public AccidentMem() {
        count = new AtomicInteger(0);
        this.accidents = new ConcurrentHashMap<>();
        int id = count.incrementAndGet();
        this.accidents.put(id, new Accident(id, "accident 1", "text 1", "addr 1"));
        id = count.incrementAndGet();
        this.accidents.put(id, new Accident(id, "accident 2", "text 2", "addr 2"));
        id = count.incrementAndGet();
        this.accidents.put(id, new Accident(id, "accident 3", "text 3", "addr 3"));
    }

    @Override
    public Collection<Accident> findAllAccidents() {
        return accidents.values();
    }

    @Override
    public Accident create(Accident accident) {
        int id = count.incrementAndGet();
        accident.setId(id);
        accidents.put(id, accident);
        return accident;
    }

    @Override
    public Accident findAccidentById(int id) {
        return null;
    }

    @Override
    public Accident updateAccident(Accident accident) {
        accidents.put(accident.getId(), accident);
        return accidents.get(accident.getId());
    }

    @Override
    public void deleteAccident(Accident accident) {

    }
}
