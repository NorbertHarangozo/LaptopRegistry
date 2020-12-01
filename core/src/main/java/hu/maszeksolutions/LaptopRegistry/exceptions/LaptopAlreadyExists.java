package hu.maszeksolutions.LaptopRegistry.exceptions;

public class LaptopAlreadyExists extends Throwable
{
    public LaptopAlreadyExists(String id)
    {
        super(id);
    }
}
