package assignment.sbsc.com.controller;

import assignment.sbsc.com.model.Employee;
import assignment.sbsc.com.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Validated
public class EmployeeController {

    @Autowired
    private EmployeeRepository repository;

    // Find
    @GetMapping("/employee")
    List<Employee> findAll() {
        return repository.findAll();
    }

    // Save
    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.CREATED)
    Employee newEmployee(@RequestBody Employee newEmployee) {
        return repository.save(newEmployee);
    }

    // Find
    @GetMapping("/employee/{id}")
    Optional<Employee> findOne(@PathVariable Long id) {
        return repository.findById(id);
    }

    // Save or update
    @PutMapping("/employee/{id}")
    Employee saveOrUpdate(@RequestBody Employee newEmployee, @PathVariable Long id) {

        return repository.findById(id)
                .map(x -> {
                    x.setName(newEmployee.getName());
                    x.setUsername(newEmployee.getUsername());
                    x.setGender(newEmployee.getGender());
                    x.setHeight(newEmployee.getHeight());
                    return repository.save(x);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });
    }

    // Delete
    @DeleteMapping("/employee/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }

}