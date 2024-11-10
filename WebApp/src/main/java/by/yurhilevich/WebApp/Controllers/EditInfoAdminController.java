package by.yurhilevich.WebApp.controllers;

import by.yurhilevich.WebApp.models.Product;
import by.yurhilevich.WebApp.service.EmployeeService;
import by.yurhilevich.WebApp.service.GroupService;
import by.yurhilevich.WebApp.service.PriceService;
import by.yurhilevich.WebApp.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Controller
@RequestMapping("/admin")
public class EditInfoAdminController {

    @Autowired
    ProductService productService;

    @Autowired
    GroupService groupService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    PriceService priceService;


    @GetMapping("/admin_dashboard_edit")
    public String admin_dashboard_edit(Model model) {
        addAttributes(model);
        return "admin/admin_dashboard_edit";
    }

    @PostMapping("/updateProduct")
    public String updateProduct(@RequestParam("productId") Long productId, @RequestParam("productName") String productName,
                                @RequestParam("sort") String sort, @RequestParam("group") String group, Model model) {
        if (productService.updateProduct(productId, productName, sort, group)) {
            return "redirect:/admin/admin_dashboard_edit?success_edit_product=true";
        }
        return "redirect:/admin/admin_dashboard_edit?failed_edit_product=true";
    }


    @PostMapping("/updatePrices")
    public String updatePrice(@RequestParam("priceId") Long priceId, @RequestParam("purchase_price") Double purchasePrice,
                              @RequestParam("selling_price") Double sellingPrice, @RequestParam("selectedDate") LocalDate date,
                              @RequestParam("product") String productName, @RequestParam("employee") String employeeName, Model model) {
        if (priceService.updatePrice(priceId, purchasePrice, sellingPrice, date, productName, employeeName)) {
            System.out.println("Success");
            return "redirect:/admin/admin_dashboard_edit?success_edit_price=true";
        }
        return "redirect:/admin/admin_dashboard_edit?failed_edit_price=true";
    }

    public void addAttributes(Model model) {
        model.addAttribute("prices", priceService.getAllPrices());
        model.addAttribute("products", productService.getAll());
        model.addAttribute("products_str", productService.getAllProducts());
        model.addAttribute("employees", employeeService.getAllEmployees());
        model.addAttribute("groupTypes", groupService.getAllGroups());
    }
}
