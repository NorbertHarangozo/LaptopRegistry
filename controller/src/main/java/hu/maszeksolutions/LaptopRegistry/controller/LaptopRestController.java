package hu.maszeksolutions.LaptopRegistry.controller;

import hu.maszeksolutions.LaptopRegistry.exceptions.LaptopNotFound;
import hu.maszeksolutions.LaptopRegistry.model.Laptop;
import hu.maszeksolutions.LaptopRegistry.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(value = "/REST/")
public class LaptopRestController
{
    @Autowired
    LaptopService service;

    @GetMapping(value = "Laptops")
    public Collection<Laptop> getLaptops()
    {
        return service.getAllLaptops();
    }

    @GetMapping(value = "Laptop/{serialNumber}")
    public Laptop getLaptopBySerialNumber(@PathVariable String serialNumber) throws LaptopNotFound
    {
        return service.getLaptop(serialNumber);
    }
}
