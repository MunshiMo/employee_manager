import { Component, OnInit } from '@angular/core';
import { Employee } from './employee';
import { EmployeeService } from './employee.service';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  employees: Employee[] = [];
  title = 'employeemanagerapp'

  editEmployee: Employee | null = null;
  deleteEmployee: Employee | null = null;
  filteredEmployees = this.employees; // Array to hold the filtered list of employees for searching


  constructor(private employeeService: EmployeeService) {}

  ngOnInit() {
    this.getEmployees();
    //console.log(this.employees); // Add this line to check the employees array
  }

      // Generic method to handle errors
  private handleError(error: HttpErrorResponse): void {
        alert(error.message);
    }

  public getEmployees(): void {
    console.log("components initalized");
    this.employeeService.getEmployees().subscribe({
      next: (response: Employee[]) => (
        this.employees = response), error: this.handleError});
  }

  public onAddEmployee(addForm: NgForm): void {
    document.getElementById('add-employee-form')?.click();
    this.employeeService.addEmployee(addForm.value).subscribe({
      next:(response: Employee) => {
        console.log(response);
        this.getEmployees();
        addForm.reset()
      },
      error: (error) => {
      this.handleError(error);
      addForm.reset();// reset form whether successful or not
      }
    });
    
  }

  public onUpdateEmployee(employee: Employee): void {
    //document.getElementById('add-employee-form')?.click();
    this.closeModal('add-employee-form');
    this.employeeService.updateEmployee(employee).subscribe({
      next: (response: Employee) => {
        console.log(response);
        this.getEmployees();
      },
      error:this.handleError

      });
    
  }

  public onDeleteEmployee(employeeId?: number): void {
    this.closeModal('add-employee-form');
    if (employeeId === undefined) {
      console.error('Employee ID is undefined');
      return;
    }
    this.employeeService.deleteEmployee(employeeId).subscribe({
      next: () =>{
        console.log('Employee deleted successfully ${employeeId');
        this.getEmployees();
      },
      error: this.handleError
    
     });
    
  }

  public searchEmployees(key :string): void {
    const results: Employee[] = [];
    for (const employee of this.employees) {
      if(employee.name.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || employee.email.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || employee.phone.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || employee.jobTitle.toLowerCase().indexOf(key.toLowerCase()) !== -1) {
        results.push(employee) ;
      }
    }
    this.employees = results;
    if (results.length === 0 || !key) {
      this.getEmployees();
    }
  }
  

  public onOpenModal(employee: Employee | null, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button' ;//change the type from submit to button
    button.style.display = 'none' ;
    button.setAttribute('data-toggle','modal') ; //change data toggle to modal
    //id is going to be dynamic
    //check mode user is in
    if (mode === 'add') {
      button.setAttribute('data-target', '#addEmployeeModal');
    }
    if (mode === 'edit') {
      if (employee) {
      this.editEmployee = employee; }
      button.setAttribute('data-target', '#updateEmployeeModal');
    }
    if (mode === 'delete') {
      if (employee) {
        this.deleteEmployee = employee;
      }
      button.setAttribute('data-target', '#deleteEmployeeModal');
    }
    container?.appendChild(button);
    button.click();
    container?.removeChild(button);
  }

  private closeModal(elementId: string): void {
    document.getElementById(elementId)?.click();
}
}
