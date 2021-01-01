package hu.maszeksolutions.LaptopRegistry.service;

import hu.maszeksolutions.LaptopRegistry.exceptions.LaptopAlreadyExists;
import hu.maszeksolutions.LaptopRegistry.exceptions.LaptopNotFound;
import hu.maszeksolutions.LaptopRegistry.model.*;

import java.time.LocalDate;
import java.util.Collection;

public interface LaptopService
{
    void addLaptop(Laptop laptop) throws LaptopAlreadyExists;
    Laptop getLaptop(String serialNumber) throws LaptopNotFound;
    Collection<Laptop> getAllLaptops();
    void updateLaptop(Laptop laptop) throws LaptopNotFound;
    void removeLaptop(Laptop laptop) throws LaptopNotFound;
    Collection<Laptop> getPremiumLaptops();
    Collection<Laptop> getLaptopsByParam(Manufacturer manufacturer, String name, Color color, Double screenSize,
                                         PanelType panelType, Integer resolutionX, Integer resolutionY, Integer refreshRate,
                                         String cpu, MemoryType memoryType, Integer memorySize, String gpu,
                                         StorageType storageType, Integer storageSize, Boolean opticalDrive,
                                         Integer numberOfUSBPorts, LocalDate manufactureDate);
    int averageMemorySize();
    double averageUSBPortCount();
}
