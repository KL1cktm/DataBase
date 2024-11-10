package by.yurhilevich.WebApp.controllers;

import by.yurhilevich.WebApp.repository.ProductRepository;
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
public class AnalystAvgViewController {

    @Autowired
    ProductService productService;

    @Autowired
    DAOService daoService;

    @GetMapping("/analyst_avg_view")
    public String analystAvgView(Model model) {
        addAttributes(model);
        return "analyst/analyst_avg_view";
    }

    @PostMapping("/printAvg")
    public String printAvg(Model model) {
        System.out.println("Controller is work!");
        List<Object[]> averagePrices = daoService.getAveragePricesFromRegions();
        List<String> columns = Arrays.asList("Закупочная цена", "Отпускная цена", "Регион");
        model.addAttribute("tablePrint", true);
        model.addAttribute("tableName", "Средние цены на продукцию по регионам");
        model.addAttribute("columns", columns);
        model.addAttribute("values", averagePrices);
        System.out.println("Controller is work!");
        return "/analyst/analyst_avg_view";
    }

    @PostMapping("/viewByDate")
    public String write(@RequestParam("selectedDate") LocalDate date, @RequestParam("products") String products, Model model) {
        System.out.println("Controller start yours work");
        List<Object[]> averagePrices = daoService.getPricesByDate(date, products);
        List<String> columns = Arrays.asList("Отпускная цена", "Закупочная цена", "Регион", "Название товара", "Дата");
        List<String> valueFromRequest = Arrays.asList(products.toString(), date.toString());
        model.addAttribute("tablePrint", true);
        model.addAttribute("tableName", "Цены на товары установленные по заданной дате");
        model.addAttribute("columns", columns);
        model.addAttribute("values", averagePrices);
        model.addAttribute("valuesFromRequest", valueFromRequest);
        addAttributes(model);
        System.out.println("Controller is work!");
        return "/analyst/analyst_avg_view";
    }

    public void addAttributes(Model model) {
        model.addAttribute("products", productService.getAllProducts());
    }
}
