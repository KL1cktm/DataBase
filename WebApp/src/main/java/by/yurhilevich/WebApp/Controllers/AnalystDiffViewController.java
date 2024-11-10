package by.yurhilevich.WebApp.controllers;

import by.yurhilevich.WebApp.service.DAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/analyst")
public class AnalystDiffViewController {
    @Autowired
    DAOService daoService;

    @GetMapping("/analyst_diff")
    public String analystPriceView(Model model) {
        List<Object[]> diffPrices = daoService.getDiffPrices();
        List<String> columns = Arrays.asList("Название товара","Дата", "Прибыль", "Сотрудник", "Комбинат");
        model.addAttribute("tablePrint", true);
        model.addAttribute("tableName", "Цены на товары установленные по заданной дате");
        model.addAttribute("columns", columns);
        model.addAttribute("values", diffPrices);
        return "analyst/analyst_diff";
    }
}
