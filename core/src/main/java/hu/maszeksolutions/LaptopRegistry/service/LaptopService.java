package hu.maszeksolutions.LaptopRegistry.service;

import hu.maszeksolutions.LaptopRegistry.exceptions.LaptopAlreadyExists;
import hu.maszeksolutions.LaptopRegistry.exceptions.LaptopNotFound;
import hu.maszeksolutions.LaptopRegistry.model.*;

import java.util.Collection;

public interface LaptopService
{
    void addLaptop(Laptop laptop) throws LaptopAlreadyExists;
    Laptop getLaptop(String serialNumber) throws LaptopNotFound;
    Collection<Laptop> getAllLaptops();
    void updateLaptop(Laptop laptop) throws LaptopNotFound;
    void removeLaptop(Laptop laptop) throws LaptopNotFound;
    Collection<Laptop> getLaptopsByColor(Color color);
    Collection<Laptop> getLaptopsByManufacturer(Manufacturer manufacturer);
    Collection<Laptop> getPremiumLaptops();
    int averageMemorySize();
    double averageUSBPortCount();
}
