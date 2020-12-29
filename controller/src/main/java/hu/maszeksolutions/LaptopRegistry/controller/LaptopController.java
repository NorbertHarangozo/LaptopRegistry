package hu.maszeksolutions.LaptopRegistry.controller;

import hu.maszeksolutions.LaptopRegistry.exceptions.LaptopAlreadyExists;
import hu.maszeksolutions.LaptopRegistry.exceptions.LaptopNotFound;
import hu.maszeksolutions.LaptopRegistry.model.*;
import hu.maszeksolutions.LaptopRegistry.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @ExceptionHandler(LaptopNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String unknownLaptopId(Model model, LaptopNotFound ex)
    {
        String errorMessage = "No laptop with the following serial number can be found: " + ex.getMessage();
        model.addAttribute("errorMessage", errorMessage);

        return "error.jsp";
    }

    @GetMapping(value = "NewLaptop")
    public String addLaptopForm(Model model)
    {
        model.addAttribute("method", "NewLaptop");
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

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String invalidData(Model model, BindException ex)
    {
        List<FieldError> errors = ex.getFieldErrors();
        List<String> fields = new ArrayList<>();

        for (FieldError error: errors)
        {
            fields.add(error.getField());
        }

        String errorMessage = "The value of the following fields is invalid: " + fields.toString();
        model.addAttribute("errorMessage", errorMessage);

        return "error.jsp";
    }

    @GetMapping(value = "UpdateLaptop/{serialNumber}")
    public String updateLaptopForm(@PathVariable String serialNumber, Model model) throws LaptopNotFound
    {
        model.addAttribute("method", "UpdateLaptop");
        model.addAttribute("laptop", service.getLaptop(serialNumber));
        model.addAttribute("colors", Color.values());
        model.addAttribute("manufacturers", Manufacturer.values());
        model.addAttribute("memoryTypes", MemoryType.values());
        model.addAttribute("panelTypes", PanelType.values());
        model.addAttribute("storageTypes", StorageType.values());

        return "laptopForm.jsp";
    }

    @PostMapping(value = "UpdateLaptop/{serialNumber}")
    public String updateLaptop(@ModelAttribute("laptop") Laptop laptop) throws LaptopNotFound
    {
        service.updateLaptop(laptop);

        return "redirect:/Laptop/" + laptop.getSerialNumber();
    }

    @GetMapping(value = "DeleteLaptop/{serialNumber}")
    public String deleteLaptop(@ModelAttribute("laptop") Laptop laptop) throws LaptopNotFound
    {
        service.removeLaptop(laptop);

        return "redirect:/Laptops";
    }
}
