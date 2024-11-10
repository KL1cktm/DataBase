package by.yurhilevich.WebApp.controllers.directors;

import by.yurhilevich.WebApp.service.CombineService;
import by.yurhilevich.WebApp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/director")
public class DirectorDeleteController {

    @Autowired
    CombineService combineService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/director_dashboard_delete")
    public String director_dashboard_delete(Model model) {
        addAttributes(model);
        return "/director/director_dashboard_delete";
    }

    @PostMapping("/deleteCombine")
    public String deleteCombine(@RequestParam("combineId") Long id, Model model) {
        combineService.deleteCombine(id);
        return "redirect:/director/director_dashboard_delete";
    }

    @PostMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam("employeeId") Long id, Model model) {
        employeeService.deleteEmployee(id);
        return "redirect:/director/director_dashboard_delete";
    }

    public void addAttributes(Model model) {
        model.addAttribute("combines", combineService.getAll());
        model.addAttribute("employees", employeeService.getAll());
    }
}
