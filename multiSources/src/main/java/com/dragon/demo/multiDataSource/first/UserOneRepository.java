package com.dragon.demo.multiDataSource.first;

import org.springframework.data.repository.CrudRepository;

public interface UserOneRepository extends CrudRepository<UserOne, Integer> {
}
