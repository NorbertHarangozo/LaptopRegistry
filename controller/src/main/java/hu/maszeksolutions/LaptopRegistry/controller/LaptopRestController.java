package hu.maszeksolutions.LaptopRegistry.controller;

import hu.maszeksolutions.LaptopRegistry.exceptions.LaptopAlreadyExists;
import hu.maszeksolutions.LaptopRegistry.exceptions.LaptopNotFound;
import hu.maszeksolutions.LaptopRegistry.model.*;
import hu.maszeksolutions.LaptopRegistry.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

@RestController
@RequestMapping(value = "/REST/")
public class LaptopRestController
{
    @Autowired
    LaptopService service;

    @GetMapping(value = "Laptops")
    public Collection<Laptop> getLaptops(@RequestParam(required = false) Manufacturer manufacturer,
                                         @RequestParam(required = false) String name,
                                         @RequestParam(required = false) Color color,
                                         @RequestParam(required = false) Double screenSize,
                                         @RequestParam(required = false) PanelType panelType,
                                         @RequestParam(required = false) Integer resolutionX,
                                         @RequestParam(required = false) Integer resolutionY,
                                         @RequestParam(required = false) Integer refreshRate,
                                         @RequestParam(required = false) String cpu,
                                         @RequestParam(required = false) MemoryType memoryType,
                                         @RequestParam(required = false) Integer memorySize,
                                         @RequestParam(required = false) String gpu,
                                         @RequestParam(required = false) StorageType storageType,
                                         @RequestParam(required = false) Integer storageSize,
                                         @RequestParam(required = false) Boolean opticalDrive,
                                         @RequestParam(required = false) Integer numberOfUSBPorts,
                                         @RequestParam(required = false) String manufactureDate)
    {
        LocalDate date = null;

        if (manufactureDate != null)
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            try
            {
                date = LocalDate.parse(manufactureDate, formatter);
            }
            catch (Exception ignored)
            { }
        }

        Collection<Laptop> laptops = service.getLaptopsByParam(manufacturer, name, color, screenSize, panelType,
                                            resolutionX, resolutionY, refreshRate, cpu, memoryType, memorySize, gpu,
                                            storageType, storageSize, opticalDrive, numberOfUSBPorts, date);

        return laptops;
    }

    @GetMapping(value = "Laptop/{serialNumber}")
    public Laptop getLaptopBySerialNumber(@PathVariable String serialNumber) throws LaptopNotFound
    {
        return service.getLaptop(serialNumber);
    }

    @PostMapping(value = "AddLaptop", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addLaptop(@RequestBody Laptop laptop) throws LaptopAlreadyExists
    {
        service.addLaptop(laptop);
    }

    @PostMapping(value = "UpdateLaptop", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateLaptop(@RequestBody Laptop laptop) throws LaptopNotFound
    {
        service.updateLaptop(laptop);
    }

    @DeleteMapping(value = "DeleteLaptop/{serialNumber}")
    public void deleteLaptop(@PathVariable String serialNumber) throws LaptopNotFound
    {
        Laptop laptop = service.getLaptop(serialNumber);

        service.removeLaptop(laptop);
    }

    @ExceptionHandler(LaptopAlreadyExists.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String serialNumberAlreadyExists(LaptopAlreadyExists laptopAlreadyExists)
    {
        return "A laptop with the following serial number already exists: " + laptopAlreadyExists.getMessage();
    }

    @ExceptionHandler(LaptopNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String unknownSerialNumber(LaptopNotFound laptopNotFound)
    {
        return "No laptop with the following serial number can be found: " + laptopNotFound.getMessage();
    }
}
