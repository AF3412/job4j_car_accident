package ru.af3412.accident.repository;

import org.springframework.data.repository.CrudRepository;
import ru.af3412.accident.model.Rule;

public interface RulesJpaRepository extends CrudRepository<Rule, Integer> {
}
