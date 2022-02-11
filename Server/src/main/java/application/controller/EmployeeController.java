package application.controller;

import application.exception.EmployeeNotFoundAdvice;
import application.exception.EmployeeNotFoundException;
import application.models.EmployeeModel;
import application.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private final EmployeeRepository repository;

    EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/employees")
    List<EmployeeModel> getAllEmployees() {
        return repository.findAll();
    }

    @PostMapping("/employee")
    EmployeeModel newEmployee(@RequestBody EmployeeModel newEmployee) {
        System.out.println("posteado nuevo usuario");
        return repository.save(newEmployee);
    }

    @DeleteMapping("/employee/{id}")
    void deleteEmployee(@PathVariable Integer id) {
        repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        repository.deleteById(id);
    }
}

