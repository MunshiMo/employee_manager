package tech.getarrays.employeemanager.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tech.getarrays.employeemanager.exception.UserNotFoundException;
import tech.getarrays.employeemanager.model.Employee;
import tech.getarrays.employeemanager.repo.EmployeeRepo;

@Service
@Transactional
public class EmployeeService {
	private final EmployeeRepo employeeRepo;
	
	@Autowired
	public EmployeeService(EmployeeRepo employeeRepo) {
		this.employeeRepo = employeeRepo;
	}
	
	public Employee addEmployee(Employee employee) { 
		employee.setEmployeeCode(UUID.randomUUID().toString());
		//uuid is a java util random generator we can use to make a unique id
		return employeeRepo.save(employee);
	}
	
	
	//need method to return list of employees
	public List<Employee> findAllEmployees() {
		System.out.println("finding all employee");
		return employeeRepo.findAll();
	}
	
	//update employee info
	public Employee updateEmployee(Employee employee) {
		return employeeRepo.save(employee);
	}
	
	public void deleteEmployee(Long id) {
		employeeRepo.deleteEmployeeById(id);
	}
	
	public Employee findEmployeeById(Long id) {
		return employeeRepo.findEmployeeById(id).orElseThrow(() -> new UserNotFoundException ("User by id " + id + " was not found "));
		
	}

}
