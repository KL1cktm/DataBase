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
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/analyst")
public class AnalystPricesViewController {

    @Autowired
    DAOService daoService;

    @GetMapping("/analyst_prices_view")
    public String analystPricesView(Model model) {
        return "analyst/analyst_prices_view";
    }

    @PostMapping("/viewPrices")
    public String viewPrices(@RequestParam("selectedDate") LocalDate date, Model model) {
        List<Object[]> dynamicPrices = daoService.getPriceWithCombines(date);
        List<String> columns = Arrays.asList("Название товара", "Дата", "Закупочная цена", "Отпускная цена", "Комбинат");
        model.addAttribute("tablePrint", true);
        model.addAttribute("tableName", "Динамика цен на товары");
        model.addAttribute("columns", columns);
        model.addAttribute("values", dynamicPrices);
        System.out.println("Controller is work!");
        return "/analyst/analyst_prices_view";
    }
}
