package by.yurhilevich.WebApp.controllers;

import by.yurhilevich.WebApp.service.EmployeeService;
import by.yurhilevich.WebApp.service.GroupService;
import by.yurhilevich.WebApp.service.PriceService;
import by.yurhilevich.WebApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller()
@RequestMapping("/admin")
public class DeleteAdminController {

    @Autowired
    ProductService productService;

    @Autowired
    GroupService groupService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    PriceService priceService;

    @GetMapping("/admin_dashboard_delete")
    public String admin_dashboard_delete(Model model) {
        addAttributes(model);
        return "admin/admin_dashboard_delete";
    }

    @PostMapping("/deleteProduct")
    public String deleteProduct(@RequestParam("productId") Long productId, Model model) {
        productService.deleteProduct(productId);
        return "redirect:/admin/admin_dashboard_delete";
    }

    @PostMapping("/deletePrices")
    public String deletePrice(@RequestParam("priceId") Long productId, Model model) {
        priceService.deletePrice(productId);
        return "redirect:/admin/admin_dashboard_delete";
    }

    public void addAttributes(Model model) {
        model.addAttribute("prices", priceService.getAllPrices());
        model.addAttribute("products", productService.getAll());
        model.addAttribute("products_str", productService.getAllProducts());
        model.addAttribute("employees", employeeService.getAllEmployees());
        model.addAttribute("groupTypes", groupService.getAllGroups());
    }
}
