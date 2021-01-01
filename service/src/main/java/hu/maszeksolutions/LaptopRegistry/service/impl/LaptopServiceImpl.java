package hu.maszeksolutions.LaptopRegistry.service.impl;

import hu.maszeksolutions.LaptopRegistry.dao.LaptopDAO;
import hu.maszeksolutions.LaptopRegistry.exceptions.LaptopAlreadyExists;
import hu.maszeksolutions.LaptopRegistry.exceptions.LaptopNotFound;
import hu.maszeksolutions.LaptopRegistry.model.*;
import hu.maszeksolutions.LaptopRegistry.service.LaptopService;

import java.time.LocalDate;
import java.util.Collection;

public class LaptopServiceImpl implements LaptopService
{
    private LaptopDAO dao;

    public LaptopServiceImpl(LaptopDAO dao)
    {
        this.dao = dao;
    }

    @Override
    public void addLaptop(Laptop laptop) throws LaptopAlreadyExists
    {
        dao.createLaptop(laptop);
    }

    @Override
    public Laptop getLaptop(String serialNumber) throws LaptopNotFound
    {
        return dao.readLaptop(serialNumber);
    }

    @Override
    public Collection<Laptop> getAllLaptops()
    {
        return dao.readAllLaptops();
    }

    @Override
    public void updateLaptop(Laptop laptop) throws LaptopNotFound
    {
        dao.updateLaptop(laptop);
    }

    @Override
    public void removeLaptop(Laptop laptop) throws LaptopNotFound
    {
        dao.deleteLaptop(laptop);
    }

    @Override
    public Collection<Laptop> getPremiumLaptops()
    {
        Collection<Laptop> laptops = dao.readLaptopsByParam(null, null, null, null,
                                        PanelType.IPS, 1920, 1080, null, null,
                                        null, 8192, null, StorageType.SSD, null,
                                        null, null, null);

        return laptops;
    }

    @Override
    public Collection<Laptop> getLaptopsByParam(Manufacturer manufacturer, String name, Color color, Double screenSize,
                                                PanelType panelType, Integer resolutionX, Integer resolutionY, Integer refreshRate,
                                                String cpu, MemoryType memoryType, Integer memorySize, String gpu,
                                                StorageType storageType, Integer storageSize, Boolean opticalDrive,
                                                Integer numberOfUSBPorts, LocalDate manufactureDate)
    {
        Collection<Laptop> laptops = dao.readLaptopsByParam(manufacturer, name, color, screenSize, panelType,
                                            resolutionX, resolutionY, refreshRate, cpu, memoryType, memorySize, gpu,
                                            storageType, storageSize, opticalDrive, numberOfUSBPorts, manufactureDate);

        return laptops;
    }

    @Override
    public int averageMemorySize()
    {
        Collection<Laptop> laptops = getAllLaptops();
        int sum = 0;

        for (Laptop l: laptops)
            sum += l.getMemorySize();

        return sum / laptops.size();
    }

    @Override
    public double averageUSBPortCount()
    {
        Collection<Laptop> laptops = getAllLaptops();

        return laptops.stream().mapToDouble(l -> l.getNumberOfUSBPorts()).average().getAsDouble();
    }
}