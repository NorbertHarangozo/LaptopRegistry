package hu.maszeksolutions.LaptopRegistry.controller;

import hu.maszeksolutions.LaptopRegistry.exceptions.LaptopAlreadyExists;
import hu.maszeksolutions.LaptopRegistry.exceptions.LaptopNotFound;
import hu.maszeksolutions.LaptopRegistry.model.*;
import hu.maszeksolutions.LaptopRegistry.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LaptopController
{
    @Autowired
    @Qualifier("laptopService")
    LaptopService service;

    @ModelAttribute(value = "laptop")
    public Laptop create()
    {
        return new Laptop();
    }

    @GetMapping(value = "/Laptops")
    public String getLaptops(Model model)
    {
        model.addAttribute("laptops", service.getAllLaptops());
        return "laptoplist.jsp";
    }

    @GetMapping(value = "/Laptop/{serialNumber}")
    public String getLaptop(@PathVariable String serialNumber, Model model) throws LaptopNotFound
    {
        model.addAttribute("laptop", service.getLaptop(serialNumber));

        return "laptopdetails.jsp";
    }

    @GetMapping(value = "NewLaptop")
    public String addLaptopForm(Model model)
    {
        model.addAttribute("colors", Color.values());
        model.addAttribute("manufacturers", Manufacturer.values());
        model.addAttribute("memoryTypes", MemoryType.values());
        model.addAttribute("panelTypes", PanelType.values());
        model.addAttribute("storageTypes", StorageType.values());

        return "laptopForm.jsp";
    }

    @PostMapping(value = "NewLaptop")
    public String addLaptop(@ModelAttribute("laptop") Laptop laptop) throws LaptopAlreadyExists {
        service.addLaptop(laptop);

        return "redirect:Laptop/" + laptop.getSerialNumber();
    }
}
