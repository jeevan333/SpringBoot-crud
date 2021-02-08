package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dao.EmployeeRepository;
import com.example.demo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeRepository employeeDAO;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository theemployeeDAO) {
		employeeDAO = theemployeeDAO;
	}

	@Override
	public List<Employee> findAll() {
		return employeeDAO.findAllByOrderByLastNameAsc();
	}

	@Override
	public Employee findById(int theId) {
		Optional<Employee> result=employeeDAO.findById(theId);
		
		Employee theEmployee=null;
		
		if(result.isPresent()) {
			theEmployee=result.get();
		}
		else {
			//we didn't find the employee
			throw new RuntimeException("Did not find employee id -"+theId);
		}
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
		employeeDAO.save(theEmployee);
	}

	@Override
	public void deleteById(int theId) {
		employeeDAO.deleteById(theId);
	}
}
