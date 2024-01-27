package com.musa.todo.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.musa.todo.model.Todo;
import com.musa.todo.service.todoservice;

@Controller
public class todocontroller {

	@Autowired
	private todoservice service;

	@GetMapping({"/", "viewToDoList"})
	public String viewAllTodoItems(Model model, @ModelAttribute("message") String message) {
		model.addAttribute("list", service.getAllTodoItems());
		model.addAttribute("message", message);
		
		return "ViewToDoList";
	}

	@GetMapping("/updateToDoStatus/{id}")
	public String updateToDoStatus(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		if (service.updateStatus(id)) {
			redirectAttributes.addFlashAttribute("message", "Update Success");
			return "redirect:/viewToDoList";
		}
		
		redirectAttributes.addFlashAttribute("message", "Update Failure");
		return "redirect:/viewToDoList";
	}

	@GetMapping("/addToDoItem")
	public String addTodoItem(Model model) {
		model.addAttribute("todo", new Todo());
		
		return "AddToDoItem";
	}

	@PostMapping("/saveToDoItem")
	public String saveTodoItem(Todo todo, RedirectAttributes redirectAttributes) {
		if(service.saveOrUpdateTodoItem(todo)) {
			redirectAttributes.addFlashAttribute("message", "Save Success");
			return "redirect:/viewToDoList";
		}
		
		redirectAttributes.addFlashAttribute("message", "Save Failure");
		return "redirect:/addToDoItem";
	}
	
	@GetMapping("/editToDoItem/{id}")
	public String editTodoItem(@PathVariable Long id, Model model) {
		model.addAttribute("todo", service.getTodoItemById(id));
		
		return "EditToDoItem";
	}

	@PostMapping("/editSaveToDoItem")
	public String editSaveToDoItem(Todo todo, RedirectAttributes redirectAttributes) {
		if(service.saveOrUpdateTodoItem(todo)) {
			redirectAttributes.addFlashAttribute("message", "Edit Success");
			return "redirect:/viewToDoList";
		}
		
		redirectAttributes.addFlashAttribute("message", "Edit Failure");
		return "redirect:/editToDoItem/" + todo.getId();
	}
	
	@GetMapping("/deleteToDoItem/{id}")
	public String deleteToDoItem(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		if (service.deleteTodoItem(id)) {
			redirectAttributes.addFlashAttribute("message", "Delete Success");
			return "redirect:/viewToDoList";
		}
		
		redirectAttributes.addFlashAttribute("message", "Delete Failure");
		return "redirect:/viewToDoList";
	}
	
}
