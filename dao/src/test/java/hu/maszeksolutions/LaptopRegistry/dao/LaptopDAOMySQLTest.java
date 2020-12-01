package hu.maszeksolutions.LaptopRegistry.dao;

import hu.maszeksolutions.LaptopRegistry.exceptions.*;
import hu.maszeksolutions.LaptopRegistry.model.*;
import hu.maszeksolutions.LaptopRegistry.model.Color;
import org.junit.Test;

import java.time.LocalDate;

public class LaptopDAOMySQLTest
{
    @Test
    public void TestDBConnection()
    {
        LaptopDAO dao = new LaptopDAOMySQL();
    }

    @Test
    public void CreateLaptopTest() throws InvalidManufactureDate, InvalidGPU, InvalidCPU, InvalidMemorySize,
            InvalidScreenSize, InvalidRefreshRate, InvalidUSBPortCount, InvalidResolution, InvalidStorageSize, InvalidName, LaptopNotFound, LaptopAlreadyExists {
        LaptopDAO dao = new LaptopDAOMySQL();
        Laptop laptop = DummyLaptop();
        dao.createLaptop(laptop);
        System.out.print("New laptop has been added: " +dao.readLaptop(laptop.getSerialNumber()));
    }

    @Test
    public void ReadLaptopTest() throws InvalidManufactureDate, InvalidGPU, InvalidCPU, InvalidMemorySize,
            InvalidScreenSize, InvalidRefreshRate, InvalidUSBPortCount, InvalidResolution, InvalidStorageSize, InvalidName, LaptopNotFound, LaptopAlreadyExists {
        LaptopDAO dao = new LaptopDAOMySQL();
        Laptop laptop = DummyLaptop();
        dao.createLaptop(laptop);
        System.out.print(dao.readLaptop(laptop.getSerialNumber()));
    }

    @Test
    public void ReadAllLaptopsTest()
    {
        LaptopDAO dao = new LaptopDAOMySQL();
        System.out.print(dao.readAllLaptops());
    }

    @Test
    public void UpdateLaptopTest() throws InvalidMemorySize, InvalidManufactureDate, InvalidGPU, InvalidCPU,
            InvalidScreenSize, InvalidRefreshRate, InvalidUSBPortCount, InvalidResolution, InvalidStorageSize, InvalidName, LaptopNotFound, LaptopAlreadyExists {
        LaptopDAO dao = new LaptopDAOMySQL();
        Laptop laptop = DummyLaptop();
        dao.createLaptop(laptop);
        System.out.println("Before update: " +dao.readLaptop(laptop.getSerialNumber()));
        laptop.setMemorySize(16384);
        dao.updateLaptop(laptop);
        System.out.print("After update: " +dao.readLaptop(laptop.getSerialNumber()));
    }

    @Test
    public void DeleteLaptopTest() throws InvalidManufactureDate, InvalidGPU, InvalidCPU, InvalidMemorySize,
            InvalidScreenSize, InvalidRefreshRate, InvalidUSBPortCount, InvalidResolution, InvalidStorageSize, InvalidName, LaptopAlreadyExists, LaptopNotFound {
        LaptopDAO dao = new LaptopDAOMySQL();
        Laptop laptop = DummyLaptop();
        dao.createLaptop(laptop);
        System.out.println("Before deletion: " +dao.readAllLaptops());
        dao.deleteLaptop(laptop);
        System.out.print("After deletion: " +dao.readAllLaptops());
    }

    @Test
    public void ReadLaptopsByColorTest()
    {
        LaptopDAO dao = new LaptopDAOMySQL();
        System.out.print(dao.readLaptopsByColor(Color.Black));
    }

    @Test
    public void ReadLaptopsByManufacturerTest()
    {
        LaptopDAO dao = new LaptopDAOMySQL();
        System.out.print(dao.readLaptopsByManufacturer(Manufacturer.Acer));
    }

    @Test
    public void ReadPremiumLaptopsTest()
    {
        LaptopDAO dao = new LaptopDAOMySQL();
        System.out.print(dao.readPremiumLaptops());
    }

    Laptop DummyLaptop() throws InvalidName, InvalidScreenSize, InvalidResolution, InvalidRefreshRate,
            InvalidCPU, InvalidMemorySize, InvalidGPU, InvalidStorageSize, InvalidUSBPortCount, InvalidManufactureDate
    {
        Laptop laptop = new Laptop();

        laptop.setManufacturer(Manufacturer.Acer);
        laptop.setName("Aspire 5 A515-51G");
        laptop.setColor(Color.Black);
        laptop.setScreenSize(15.6);
        laptop.setPanelType(PanelType.TN);
        laptop.setResolutionX(1920);
        laptop.setResolutionY(1080);
        laptop.setRefreshRate(60);
        laptop.setCpu("Intel Core i3-8130U");
        laptop.setMemoryType(MemoryType.DDR4);
        laptop.setMemorySize(8192);
        laptop.setGpu("NVIDIA GeForce MX130");
        laptop.setStorageType(StorageType.SSD);
        laptop.setStorageSize(250);
        laptop.setOpticalDrive(false);
        laptop.setNumberOfUSBPorts(3);
        laptop.setManufactureDate(LocalDate.of(2018, 11, 15));

        return laptop;
    }
}