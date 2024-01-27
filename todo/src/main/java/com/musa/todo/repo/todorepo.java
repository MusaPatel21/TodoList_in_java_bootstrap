package com.musa.todo.repo;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import  com.musa.todo.model.Todo;

@Repository
public interface todorepo extends JpaRepository<Todo, Long>{

}

