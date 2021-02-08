package com.example.demo.Controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	private EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	//add mapping for list
	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		
		//get employee from db
		List<Employee> theEmployees=employeeService.findAll();
		
		//add to the spring model
		theModel.addAttribute("employees",theEmployees);
		
		return "employees/list-employees";
	}
	
	@GetMapping("/showFormForAdd")
	public String shoeFormForAdd(Model theModel) {
		//create model attribute to binnd the data
		Employee theEmployee =new Employee();
		
		theModel.addAttribute("employee", theEmployee);
		
		return "employees/employee-form";
	}
	
	@PostMapping("/showFormForUpdate")
	public String shoeFormForUpdate(@RequestParam("employeeId") int theId,Model theModel) {
		//get the employee data from the service
		Employee theEmployee = employeeService.findById(theId);
		
		//set employee as a model attribute to pre-populate the form
		theModel.addAttribute("employee", theEmployee);
		
		//send over to  our form
		return "employees/employee-form";
	}
	
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
		//save the employee
		employeeService.save(theEmployee);
		
		//use the redirect to prevent duplicate submission
		return "redirect:/employees/list";
	}
	
	@PostMapping("/delete")
	public String deleteEmployee(@RequestParam("employeeId") int theId) {
		//delete the employee
		employeeService.deleteById(theId);
		
		//use the redirect to employees/list
		return "redirect:/employees/list";
	}
	
}
