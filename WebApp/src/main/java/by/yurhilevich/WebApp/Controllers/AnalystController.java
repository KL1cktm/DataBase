package by.yurhilevich.WebApp.controllers;

import by.yurhilevich.WebApp.service.DAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/analyst")
public class AnalystController {

    @GetMapping("/analyst_dashboard")
    public String analystDashboard(Model model) {
        return "analyst/analyst_dashboard";
    }
}
