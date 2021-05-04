package ru.af3412.accident.repository;

import org.springframework.data.repository.CrudRepository;
import ru.af3412.accident.model.Authority;

public interface AuthorityRepository extends CrudRepository<Authority, Integer> {

    Authority findByAuthority(String authority);
}
