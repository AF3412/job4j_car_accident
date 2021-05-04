package ru.af3412.accident.repository;

import org.springframework.data.repository.CrudRepository;
import ru.af3412.accident.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}
