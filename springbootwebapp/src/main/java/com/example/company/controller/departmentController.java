package com.example.company.controller;
import com.example.company.entity.Department;
import com.example.company.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;


@Controller
public class departmentController {

    @Autowired
    private DepartmentRepository dRepo;

    @GetMapping({"/showDepartments", "/list-department"})
    public ModelAndView showDepartments() {

        ModelAndView mav = new ModelAndView("list-department");
        List <Department> list = dRepo.findAll();
        mav.addObject("departments",list);
        return mav;
    }

    @GetMapping("/addDepartmentForm")
    public ModelAndView addDepartmentForm(){
        ModelAndView mav = new ModelAndView("add-department-form");
        Department newDepartment = new Department();
        mav.addObject("department",newDepartment);
        return mav;
    }

    @PostMapping("/saveDepartment")
    public String saveDepartment(@ModelAttribute Department department) {
        dRepo.save(department);
        return "redirect:/list-department";
    }

    @GetMapping("/showDepartmentForm")
    public ModelAndView showUpdateForm(@RequestParam Long dep_id) {
        ModelAndView mav = new ModelAndView("add-department-form");
        Department department = dRepo.findById(dep_id).get();
        mav.addObject("department", department);
        return mav;
    }

    @GetMapping("/deleteDepartment")
    public String deleteEmployee(@RequestParam Long dep_id) {
        dRepo.deleteById(dep_id);
        return "redirect:/list-department";
    }

}
