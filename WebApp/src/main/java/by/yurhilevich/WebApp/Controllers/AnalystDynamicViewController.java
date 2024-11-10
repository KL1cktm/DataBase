package by.yurhilevich.WebApp.controllers;

import by.yurhilevich.WebApp.service.DAOService;
import by.yurhilevich.WebApp.service.ProductService;
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
public class AnalystDynamicViewController {

    @Autowired
    DAOService daoService;

    @Autowired
    ProductService productService;

    @GetMapping("/analyst_dynamic")
    public String analyst_dynamic(Model model) {
        addAttributes(model);
        return "analyst/analyst_dynamic";
    }

    @PostMapping("/viewDynamic")
    public String write(@RequestParam("selectedDate") LocalDate date1, @RequestParam("selectedDateStart") LocalDate date2, @RequestParam("products") String products, Model model) {
        System.out.println("Controller start yours work");
        List<Object[]> dynamicPrices = daoService.getDynamicPrices(date1, date2, products);
        List<String> columns = Arrays.asList("Название товара", "Дата", "Закупочная цена", "Отпускная цена", "Сотрудник", "Регион");
        model.addAttribute("tablePrint", true);
        model.addAttribute("tableName", "Динамика цен на товары");
        model.addAttribute("columns", columns);
        model.addAttribute("values", dynamicPrices);
        addAttributes(model);
        System.out.println("Controller is work!");
        return "/analyst/analyst_dynamic";
    }

    public void addAttributes(Model model) {
        model.addAttribute("products", productService.getAllProducts());
    }
}
