package by.yurhilevich.WebApp.controllers.directors;

import by.yurhilevich.WebApp.service.CombineService;
import by.yurhilevich.WebApp.service.EmployeeService;
import by.yurhilevich.WebApp.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/director")
public class DirectorAddController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    CombineService combineService;

    @Autowired
    RegionService regionService;

    @GetMapping("/director_dashboard_add")
    public String directorAdd(Model model) {
        addAttributes(model);
        return "director/director_dashboard_add";
    }

    @PostMapping("/createEmployee")
    public String creatEmployee(@RequestParam("employee_name") String fio,
                                @RequestParam("rang") String position,
                                @RequestParam("combines") String combineName,
                                Model model) {
        System.out.println("dsds");
        if (employeeService.addEmployee(fio, position, combineName)) {
            return "redirect:/director/director_dashboard_add?create_employee=true";
        } else {
            return "redirect:/director/director_dashboard_add?create_employee_error=true";
        }
    }

    @PostMapping("/createCombine")
    public String createCombine(@RequestParam("name") String combineName,
                                @RequestParam("address")String address,
                                @RequestParam("phone") String phone,
                                @RequestParam("regions") String regionName,
                                Model model) {
        if (combineService.addCombine(combineName, address, phone, regionName)) {
            return "redirect:/director/director_dashboard_add?combine_added=true";
        }
        return "redirect:/director/director_dashboard_add?combine_exist=true";
    }


    public void addAttributes(Model model) {
        model.addAttribute("combines", combineService.getAllCombines());
        model.addAttribute("regions", regionService.getAllRegions());
    }
}
