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
public class DirectorEditController {
    @Autowired
    CombineService combineService;

    @Autowired
    RegionService regionService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/director_dashboard_edit")
    public String director_dashboard_edit(Model model) {
        addAttributes(model);
        return "/director/director_dashboard_edit";
    }

    @PostMapping("/updateCombine")
    public String updateCombine(@RequestParam("combineId") Long id,
                                @RequestParam("combineName") String combineName,
                                @RequestParam("address") String address,
                                @RequestParam("phone") String phone,
                                @RequestParam("regions") String regionName,
                                Model model) {
        if (combineService.updateCombine(id, combineName, address, phone, regionName)) {
            return "redirect:/director/director_dashboard_edit?success_edit_product=true";
        }
        return "redirect:/director/director_dashboard_edit?failed_edit_product=true";
    }

    @PostMapping("/updateEmployee")
    public String updateEmployee(@RequestParam("employeeId") Long id,
                                 @RequestParam("fio") String fio,
                                 @RequestParam("rang") String position,
                                 @RequestParam("combines") String combines,
                                 Model model) {
        if (employeeService.updateEmployee(id, fio, position, combines)) {
            return "redirect:/director/director_dashboard_edit?success_edit_employee=true";
        }
        return "redirect:/director/director_dashboard_edit?failed_edit_employee=true";
    }

    public void addAttributes(Model model) {
        model.addAttribute("combines", combineService.getAll());
        model.addAttribute("employees", employeeService.getAll());
        model.addAttribute("combines_str", combineService.getAllCombines());
//        model.addAttribute("combines", combineService.getAllCombines());
        model.addAttribute("regions", regionService.getAllRegions());
    }
}
