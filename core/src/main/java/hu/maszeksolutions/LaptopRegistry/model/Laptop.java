package hu.maszeksolutions.LaptopRegistry.model;

import hu.maszeksolutions.LaptopRegistry.exceptions.*;

import java.time.LocalDate;
import java.util.UUID;

public class Laptop
{
    private String serialNumber;
    private Manufacturer manufacturer;
    private String name;
    private Color color;
    private double screenSize;
    private PanelType panelType;
    private int resolutionX;
    private int resolutionY;
    private int refreshRate;
    private String cpu;
    private MemoryType memoryType;
    private int memorySize;
    private String gpu;
    private StorageType storageType;
    private int storageSize;
    private boolean opticalDrive;
    private int numberOfUSBPorts;
    private LocalDate manufactureDate;

    public Laptop()
    {
        this.serialNumber = UUID.randomUUID().toString();
    }

    public String getSerialNumber()
    {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber)
    {
        this.serialNumber = serialNumber;
    }

    public Manufacturer getManufacturer()
    {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer)
    {
        this.manufacturer = manufacturer;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name) throws InvalidName {
        if (name.trim().length() == 0)
            throw new InvalidName();
        this.name = name;
    }

    public Color getColor()
    {
        return color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    public double getScreenSize()
    {
        return screenSize;
    }

    public void setScreenSize(double screenSize) throws InvalidScreenSize {
        if (screenSize < 7.0 || screenSize > 32.0)
            throw new InvalidScreenSize();
        this.screenSize = screenSize;
    }

    public PanelType getPanelType()
    {
        return panelType;
    }

    public void setPanelType(PanelType panelType)
    {
        this.panelType = panelType;
    }

    public int getResolutionX()
    {
        return resolutionX;
    }

    public void setResolutionX(int resolutionX) throws InvalidResolution {
        if (resolutionX < 400 || resolutionX > 16384)
            throw new InvalidResolution();
        this.resolutionX = resolutionX;
    }

    public int getResolutionY()
    {
        return resolutionY;
    }

    public void setResolutionY(int resolutionY) throws InvalidResolution {
        if (resolutionY < 400 || resolutionY > 16384)
            throw new InvalidResolution();
        this.resolutionY = resolutionY;
    }

    public int getRefreshRate()
    {
        return refreshRate;
    }

    public void setRefreshRate(int refreshRate) throws InvalidRefreshRate {
        if (refreshRate < 24 || refreshRate > 480)
            throw new InvalidRefreshRate();
        this.refreshRate = refreshRate;
    }

    public String getCpu()
    {
        return cpu;
    }

    public void setCpu(String cpu) throws InvalidCPU {
        if (cpu.trim().length() == 0)
            throw new InvalidCPU();
        this.cpu = cpu;
    }

    public MemoryType getMemoryType()
    {
        return memoryType;
    }

    public void setMemoryType(MemoryType memoryType)
    {
        this.memoryType = memoryType;
    }

    public int getMemorySize()
    {
        return memorySize;
    }

    public void setMemorySize(int memorySize) throws InvalidMemorySize {
        if (memorySize < 128 || memorySize > 524288)
            throw new InvalidMemorySize();
        this.memorySize = memorySize;
    }

    public String getGpu()
    {
        return gpu;
    }

    public void setGpu(String gpu) throws InvalidGPU {
        if (gpu.trim().length() == 0)
            throw new InvalidGPU();
        this.gpu = gpu;
    }

    public StorageType getStorageType()
    {
        return storageType;
    }

    public void setStorageType(StorageType storageType)
    {
        this.storageType = storageType;
    }

    public int getStorageSize()
    {
        return storageSize;
    }

    public void setStorageSize(int storageSize) throws InvalidStorageSize {
        if (storageSize < 10 || storageSize > 20480)
            throw new InvalidStorageSize();
        this.storageSize = storageSize;
    }

    public boolean isOpticalDrive()
    {
        return opticalDrive;
    }

    public void setOpticalDrive(boolean opticalDrive)
    {
        this.opticalDrive = opticalDrive;
    }

    public int getNumberOfUSBPorts()
    {
        return numberOfUSBPorts;
    }

    public void setNumberOfUSBPorts(int numberOfUSBPorts) throws InvalidUSBPortCount {
        if (numberOfUSBPorts < 1 || numberOfUSBPorts > 20)
            throw new InvalidUSBPortCount();
        this.numberOfUSBPorts = numberOfUSBPorts;
    }

    public LocalDate getManufactureDate()
    {
        return manufactureDate;
    }

    public void setManufactureDate(LocalDate manufactureDate) throws InvalidManufactureDate
    {
        if (manufactureDate.isBefore(LocalDate.of(2000, 1, 1)))
            throw new InvalidManufactureDate("Laptops manufactured before the year 2000 are not supported. The one you've entered was manufactured at: " +manufactureDate);
        if (manufactureDate.isAfter(LocalDate.now()))
            throw new InvalidManufactureDate("The laptop manufacture date you've entered is invalid, because it's pointing to a date after today: " +manufactureDate);
        this.manufactureDate = manufactureDate;
    }

    @Override
    public String toString()
    {
        return "Laptop{" +
                "serialNumber='" + serialNumber + '\'' +
                ", manufacturer=" + manufacturer +
                ", name='" + name + '\'' +
                ", color=" + color +
                ", screenSize=" + screenSize +
                ", panelType=" + panelType +
                ", resolution=" + resolutionX + "x" + resolutionY +
                ", refreshRate=" + refreshRate + " Hz" +
                ", cpu='" + cpu + '\'' +
                ", memoryType=" + memoryType +
                ", memorySize=" + memorySize + " MB" +
                ", gpu='" + gpu + '\'' +
                ", storageType=" + storageType +
                ", storageSize=" + storageSize + " GB" +
                ", opticalDrive=" + opticalDrive +
                ", numberOfUSBPorts=" + numberOfUSBPorts +
                ", manufactureDate=" + manufactureDate +
                '}';
    }
}
