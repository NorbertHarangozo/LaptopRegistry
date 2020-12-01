package hu.maszeksolutions.LaptopRegistry.exceptions;

public class LaptopNotFound extends Throwable
{
    public LaptopNotFound(String id)
    {
        super(id);
    }
}
