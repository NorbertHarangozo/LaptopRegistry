package hu.maszeksolutions.LaptopRegistry.controller;

import hu.maszeksolutions.LaptopRegistry.exceptions.LaptopNotFound;
import hu.maszeksolutions.LaptopRegistry.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LaptopController
{
    @Autowired
    @Qualifier("laptopService")
    LaptopService service;

    @GetMapping(value = "/Laptops")
    public String getLaptops(Model model)
    {
        model.addAttribute("laptops", service.getAllLaptops());
        return "laptoplist.jsp";
    }
}
