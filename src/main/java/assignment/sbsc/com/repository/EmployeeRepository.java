package assignment.sbsc.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import assignment.sbsc.com.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
}
