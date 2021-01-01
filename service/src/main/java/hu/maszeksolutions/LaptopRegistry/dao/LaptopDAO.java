package hu.maszeksolutions.LaptopRegistry.dao;

import hu.maszeksolutions.LaptopRegistry.exceptions.LaptopAlreadyExists;
import hu.maszeksolutions.LaptopRegistry.exceptions.LaptopNotFound;
import hu.maszeksolutions.LaptopRegistry.model.*;

import java.time.LocalDate;
import java.util.Collection;

public interface LaptopDAO
{
    void createLaptop(Laptop laptop) throws LaptopAlreadyExists;
    Laptop readLaptop(String serialNumber) throws LaptopNotFound;
    Collection<Laptop> readAllLaptops();
    void updateLaptop(Laptop laptop) throws LaptopNotFound;
    void deleteLaptop(Laptop laptop) throws LaptopNotFound;
    Collection<Laptop> readLaptopsByParam(Manufacturer manufacturer, String name, Color color, Double screenSize,
                                          PanelType panelType, Integer resolutionX, Integer resolutionY, Integer refreshRate,
                                          String cpu, MemoryType memoryType, Integer memorySize, String gpu,
                                          StorageType storageType, Integer storageSize, Boolean opticalDrive,
                                          Integer numberOfUSBPorts, LocalDate manufactureDate);
}
