package ru.af3412.accident.repository;

import org.springframework.data.repository.CrudRepository;
import ru.af3412.accident.model.AccidentType;

public interface AccidentTypeJpaRepository extends CrudRepository<AccidentType, Integer> {
}
