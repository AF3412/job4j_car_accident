package ru.af3412.accident.repository;

import org.springframework.data.repository.CrudRepository;
import ru.af3412.accident.model.Accident;

public interface AccidentJpaRepository extends CrudRepository<Accident, Integer> {
}
