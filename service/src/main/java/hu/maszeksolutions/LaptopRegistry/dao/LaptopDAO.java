package hu.maszeksolutions.LaptopRegistry.dao;

import hu.maszeksolutions.LaptopRegistry.exceptions.LaptopAlreadyExists;
import hu.maszeksolutions.LaptopRegistry.exceptions.LaptopNotFound;
import hu.maszeksolutions.LaptopRegistry.model.*;

import java.util.Collection;

public interface LaptopDAO
{
    void createLaptop(Laptop laptop) throws LaptopAlreadyExists;
    Laptop readLaptop(String serialNumber) throws LaptopNotFound;
    Collection<Laptop> readAllLaptops();
    void updateLaptop(Laptop laptop) throws LaptopNotFound;
    void deleteLaptop(Laptop laptop) throws LaptopNotFound;
    Collection<Laptop> readLaptopsByColor(Color color);
    Collection<Laptop> readLaptopsByManufacturer(Manufacturer manufacturer);
    Collection<Laptop> readPremiumLaptops();
}
