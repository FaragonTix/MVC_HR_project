package com.spring.webmvc.controller;


import com.spring.webmvc.dao.EmployeeDAO;
import com.spring.webmvc.entity.Employee;
import com.spring.webmvc.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MyController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/")
    public  String show_AllEmployees(Model model){
        List<Employee> allEmployees = employeeService.getAllEmployees();
        model.addAttribute("allEmps", allEmployees);
        return "allEmployeesView";
    }

    @RequestMapping("/add_new_employee")
    public String addNewEmployee(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "AddNewEmployeeView";}

    @RequestMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }
    @RequestMapping("/update_info")
    public String updateEmployee(@RequestParam("empId") int id, Model model){
    Employee employee = employeeService.getEmployee(id);
    model.addAttribute("employee", employee);
    return "AddNewEmployeeView";
    }
    @RequestMapping("/delete_info")
    public String deleteEmployee(@RequestParam("empId") int id, Model model){
        employeeService.deleteEmployee(id);
        return "redirect:/";
    }
}
