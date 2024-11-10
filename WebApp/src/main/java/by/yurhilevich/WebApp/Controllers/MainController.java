package by.yurhilevich.WebApp.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String mainPage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Добавляем роль в модель
        model.addAttribute("role", authentication.getAuthorities().iterator().next().getAuthority());

        return "home";
    }
}
