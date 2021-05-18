package com.programming.techie.springbootmongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ToDoRepository extends MongoRepository<ToDo, String> {

    List<ToDo> findByDetails(String detail);
}