package tech.getarrays.employeemanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.getarrays.employeemanager.model.Employee;

import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee, Long>{

	//need to specify the data type of primary key for ID its long
	void deleteEmployeeById(Long id);

	Optional<Employee> findEmployeeById(Long id); //query method springboot can auto make these 

}
