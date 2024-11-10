package by.yurhilevich.WebApp.controllers;

import by.yurhilevich.WebApp.service.EmployeeService;
import by.yurhilevich.WebApp.service.GroupService;
import by.yurhilevich.WebApp.service.PriceService;
import by.yurhilevich.WebApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Controller
@RequestMapping("/admin")
public class AddInfoAdminController {

    @Autowired
    private GroupService groupService;
    @Autowired
    private ProductService productService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PriceService priceService;

    @GetMapping("/admin_dashboard_add")
    public String createDashboard(Model model) {
        addAttributes(model);
        return "admin/admin_dashboard_add";
    }

    @PostMapping("/createGroup")
    public String addGroup(@RequestParam("group_name") String name, Model model) {
        if (!groupService.isGroupExists(name)) {
            groupService.saveGroup(name);
            return "redirect:/admin/admin_dashboard_add?group_added=true";
        } else {
            return "redirect:/admin/admin_dashboard_add?group_exist=true";
        }
    }

    @PostMapping("/createProduct")
    public String addProduct(@RequestParam("product_name") String name, @RequestParam("sort") String sort, @RequestParam("groups") String group, Model model) {
        if (productService.saveProduct(name, sort, group)) {
            return "redirect:/admin/admin_dashboard_add?create_product=true";
        }
        return "redirect:/admin/admin_dashboard_add?create_product_error=true";
    }

    @PostMapping("/createPrice")
    public String addPrice(@RequestParam("purchasePrice") Double purchasePrice, @RequestParam("sellingPrice") Double sellingPrice,
                           @RequestParam("products") String product, @RequestParam("employees") String employees,
                           @RequestParam(value = "priceDate", required = false) LocalDate date, Model model) {
        if (priceService.addPrice(purchasePrice, sellingPrice, product,employees, date)) {
            return "redirect:/admin/admin_dashboard_add?price_added=true";
        }
        return "redirect:/admin/admin_dashboard_add?price_exist=true";
    }

    public void addAttributes(Model model) {
        System.out.println("Method started");
        model.addAttribute("groupTypes", groupService.getAllGroups());
        model.addAttribute("employees", employeeService.getAllEmployees());
        model.addAttribute("products", productService.getAllProducts());
    }
}
