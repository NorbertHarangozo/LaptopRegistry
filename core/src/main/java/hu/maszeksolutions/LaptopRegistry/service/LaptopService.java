package hu.maszeksolutions.LaptopRegistry.service;

import hu.maszeksolutions.LaptopRegistry.model.Color;
import hu.maszeksolutions.LaptopRegistry.model.Laptop;
import hu.maszeksolutions.LaptopRegistry.model.Manufacturer;

import java.util.Collection;

public interface LaptopService
{
    void addLaptop(Laptop laptop);
    Laptop getLaptop(String serialNumber);
    Collection<Laptop> getAllLaptops();
    void updateLaptop(Laptop laptop);
    void removeLaptop(Laptop laptop);
    Collection<Laptop> getLaptopsByColor(Color color);
    Collection<Laptop> getLaptopsByManufacturer(Manufacturer manufacturer);
    Collection<Laptop> getPremiumLaptops();
}
