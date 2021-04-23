package ru.af3412.accident.repository;

import org.springframework.stereotype.Repository;
import ru.af3412.accident.model.Accident;
import ru.af3412.accident.model.AccidentType;
import ru.af3412.accident.model.Rule;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem implements AccidentRepository {

    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final Map<Integer, AccidentType> types = new HashMap<>();
    private final List<Rule> rules = new ArrayList<>();
    private final AtomicInteger count = new AtomicInteger(0);

    public AccidentMem() {
        types.put(1, AccidentType.of(1, "Две машины"));
        types.put(2, AccidentType.of(2, "Машина и человек"));
        types.put(3, AccidentType.of(3, "Машина и велосипед"));

        rules.add(Rule.of(1, "Статья. 1"));
        rules.add(Rule.of(2, "Статья. 2"));
        rules.add(Rule.of(3, "Статья. 3"));

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
    public Collection<AccidentType> findAllAccidentTypes() {
        return types.values();
    }

    @Override
    public Collection<Rule> findAllRules() {
        return Collections.unmodifiableList(rules);
    }

    @Override
    public Accident create(Accident accident) {
        int id = count.incrementAndGet();
        int typeId = accident.getType().getId();
        accident.setType(types.get(typeId));
        accident.setId(id);
        accidents.put(id, accident);
        return accident;
    }

    @Override
    public Accident findAccidentById(int id) {
        return accidents.get(id);
    }

    @Override
    public Accident updateAccident(Accident accident) {
        int typeId = accident.getType().getId();
        accident.setType(types.get(typeId));
        accidents.put(accident.getId(), accident);
        return accidents.get(accident.getId());
    }

    @Override
    public void deleteAccident(Accident accident) {

    }
}
