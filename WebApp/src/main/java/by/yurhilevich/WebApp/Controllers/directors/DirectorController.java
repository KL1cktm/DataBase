package by.yurhilevich.WebApp.controllers.directors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/director")
public class DirectorController {

    @GetMapping("/director_dashboard")
    public String director_dashboard_add(Model model) {
        return "director/director_dashboard";
    }
}
