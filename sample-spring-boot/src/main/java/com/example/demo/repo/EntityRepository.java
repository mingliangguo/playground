package com.example.demo.repo;

import com.example.demo.model.Entity;
import org.springframework.data.repository.CrudRepository;

public interface EntityRepository extends CrudRepository<Entity, String> {

}
