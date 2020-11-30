package hu.maszeksolutions.LaptopRegistry.dao;

import hu.maszeksolutions.LaptopRegistry.model.Color;
import hu.maszeksolutions.LaptopRegistry.model.Laptop;
import hu.maszeksolutions.LaptopRegistry.model.Manufacturer;

import java.util.Collection;

public interface LaptopDAO
{
    void createLaptop(Laptop laptop);
    Laptop readLaptop(String serialNumber);
    Collection<Laptop> readAllLaptops();
    void updateLaptop(Laptop laptop);
    void deleteLaptop(Laptop laptop);
    Collection<Laptop> readLaptopsByColor(Color color);
    Collection<Laptop> readLaptopsByManufacturer(Manufacturer manufacturer);
    Collection<Laptop> readPremiumLaptops();
}
