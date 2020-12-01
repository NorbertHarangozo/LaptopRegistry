package hu.maszeksolutions.LaptopRegistry.service.impl;

import hu.maszeksolutions.LaptopRegistry.dao.LaptopDAO;
import hu.maszeksolutions.LaptopRegistry.exceptions.LaptopAlreadyExists;
import hu.maszeksolutions.LaptopRegistry.exceptions.LaptopNotFound;
import hu.maszeksolutions.LaptopRegistry.model.Color;
import hu.maszeksolutions.LaptopRegistry.model.Laptop;
import hu.maszeksolutions.LaptopRegistry.model.Manufacturer;
import hu.maszeksolutions.LaptopRegistry.service.LaptopService;

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
    public Collection<Laptop> getLaptopsByColor(Color color)
    {
        return dao.readLaptopsByColor(color);
    }

    @Override
    public Collection<Laptop> getLaptopsByManufacturer(Manufacturer manufacturer)
    {
        return dao.readLaptopsByManufacturer(manufacturer);
    }

    @Override
    public Collection<Laptop> getPremiumLaptops()
    {
        return dao.readPremiumLaptops();
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