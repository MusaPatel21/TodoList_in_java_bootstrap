package com.musa.todo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musa.todo.model.Todo;
import com.musa.todo.repo.todorepo;

@Service
public class todoservice {

	
	@Autowired
	todorepo repo;

	public List<Todo> getAllTodoItems() {
		ArrayList<Todo> todoList = new ArrayList<>();
		repo.findAll().forEach(todo -> todoList.add(todo));
		
		return todoList;
	}
	
	public Todo getTodoItemById(Long id) {
		return repo.findById(id).get();
	}
	
	public boolean updateStatus(Long id) {
		Todo todo = getTodoItemById(id);
		todo.setStatus("Completed");
		
		return saveOrUpdateTodoItem(todo);
	}
	
	public boolean saveOrUpdateTodoItem(Todo todo) {
		Todo updatedObj = repo.save(todo);
		
		if (getTodoItemById(updatedObj.getId()) != null) {
			return true;
		}
		
		return false;
	}
	
	public boolean deleteTodoItem(Long id) {
		repo.deleteById(id);
		
		if (repo.findById(id).isEmpty()) {
			return true;
		}
		
		return false;
	}
	
}
