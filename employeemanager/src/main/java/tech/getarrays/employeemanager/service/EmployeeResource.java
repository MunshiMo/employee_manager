package tech.getarrays.employeemanager.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.getarrays.employeemanager.model.Employee;

//our controller representation
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/employee") //to access employee resrouce through this header
public class EmployeeResource {
	private final EmployeeService employeeService;

	public EmployeeResource(EmployeeService employeeService) {
		
		this.employeeService = employeeService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Employee>> getAllEmployees () {
		//return http response and in it is a list of employee
		List<Employee> employees = employeeService.findAllEmployees();
		//if (employees == null) {
		//	return new ResponseEntity<>(HttpStatus.NO_CONTENT); // or any other response
		//}
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

	/*@GetMapping("/all")
	public ResponseEntity<List<Employee>> getAllEmployee () {
    try {
        List<Employee> employees = employeeService.findAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    } catch (Exception e) {
        e.printStackTrace();  // or use a logger to log the exception
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}*/

	
	@GetMapping("/find/{id}") //from employee we can filter more to find id
	public ResponseEntity<Employee> getEmployeeById (@PathVariable("id") Long id) {//take from the id path we designated
		//return http response and in it is a list of employee
		Employee employee = employeeService.findEmployeeById(id);
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		Employee newEmployee = employeeService.addEmployee(employee);
		return new ResponseEntity<>(newEmployee, HttpStatus.CREATED); //CREATED new user on server
	}
	
	
	//update an existing user so we can use put to add the info and we also need the id 
	@PutMapping("/update")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
		Employee updateEmployee = employeeService.updateEmployee(employee);
		return new ResponseEntity<>(updateEmployee, HttpStatus.OK); //update user on server
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id) {
		employeeService.deleteEmployee(id);
		return new ResponseEntity<>(HttpStatus.OK); //delete user on server
	}
	
	
	
	

}
