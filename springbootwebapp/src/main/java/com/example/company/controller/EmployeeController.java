package com.example.company.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.example.company.entity.Employee;
import com.example.company.repository.EmployeeRepository;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeRepository eRepo;
	@GetMapping("")
	public String viewHomePage(){
		return "index";
	}
	@GetMapping({"/showEmployess", "/list-employee"})
	public ModelAndView showEmployees() {
		
		ModelAndView mav = new ModelAndView("list-employees");
		List<Employee> list = eRepo.findAll();
		mav.addObject("employees",list);
		return mav;
	}
	@GetMapping("/addEmployeeFormm")
	public ModelAndView addEmployeeForm(){
		ModelAndView mav = new ModelAndView("add-employee-form");
		Employee newEmployee = new Employee();
		mav.addObject("employee",newEmployee);
		return mav;
	}
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute Employee employee) {
		eRepo.save(employee);
		return "redirect:/list-employee";
	}
	@GetMapping("/showUpdateForm")
	public ModelAndView showUpdateForm(@RequestParam Long employeeId) {
		ModelAndView mav = new ModelAndView("add-employee-form");
		Employee employee = eRepo.findById(employeeId).get();
		mav.addObject("employee", employee);
		return mav;
	}
	@GetMapping("/deleteEmployee")
	public String deleteEmployee(@RequestParam Long employeeId) {
		eRepo.deleteById(employeeId);
		return "redirect:/list-employee";
	}

}
