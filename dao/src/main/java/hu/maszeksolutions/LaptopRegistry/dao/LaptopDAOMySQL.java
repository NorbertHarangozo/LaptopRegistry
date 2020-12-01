package hu.maszeksolutions.LaptopRegistry.dao;

import hu.maszeksolutions.LaptopRegistry.exceptions.LaptopAlreadyExists;
import hu.maszeksolutions.LaptopRegistry.exceptions.LaptopNotFound;
import hu.maszeksolutions.LaptopRegistry.model.Color;
import hu.maszeksolutions.LaptopRegistry.model.Laptop;
import hu.maszeksolutions.LaptopRegistry.model.Manufacturer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Collection;

public class LaptopDAOMySQL implements LaptopDAO
{
    private static SessionFactory factory;

    public LaptopDAOMySQL()
    {
        Configuration cfg = new Configuration();

        try
        {
            factory = cfg.configure().buildSessionFactory();
        }
        catch (Exception ex)
        {
            System.out.println("Database configuration process has failed. Are the credentials in your hibernate.properties file valid?");
        }
    }

    @Override
    public void createLaptop(Laptop laptop) throws LaptopAlreadyExists
    {
        Session session = factory.openSession();
        Laptop l = null;

        try
        {
            l = readLaptop(laptop.getSerialNumber());
        }
        catch (LaptopNotFound laptopNotFound)
        {
            Transaction tx = session.beginTransaction();
            session.save(laptop);
            tx.commit();
        }

        session.close();

        if (l != null)
            throw new LaptopAlreadyExists(laptop.getSerialNumber());
    }

    @Override
    public Laptop readLaptop(String serialNumber) throws LaptopNotFound {
        Session session = factory.openSession();
        Laptop result = session.get(Laptop.class, serialNumber);
        session.close();

        if (result == null)
            throw new LaptopNotFound(serialNumber);

        return result;
    }

    @Override
    public Collection<Laptop> readAllLaptops()
    {
        Session session = factory.openSession();
        Collection<Laptop> result = session.createQuery("FROM Laptop").list();
        session.close();

        return result;
    }

    @Override
    public void updateLaptop(Laptop laptop) throws LaptopNotFound
    {
        Session session = factory.openSession();

        if (readLaptop(laptop.getSerialNumber()) == null)
            throw new LaptopNotFound(laptop.getSerialNumber());

        Transaction tx = session.beginTransaction();
        session.update(laptop);
        tx.commit();
        session.close();
    }

    @Override
    public void deleteLaptop(Laptop laptop) throws LaptopNotFound
    {
        Session session = factory.openSession();

        if (readLaptop(laptop.getSerialNumber()) == null)
            throw new LaptopNotFound(laptop.getSerialNumber());

        Transaction tx = session.beginTransaction();
        session.delete(laptop);
        tx.commit();
        session.close();
    }

    @Override
    public Collection<Laptop> readLaptopsByColor(Color color)
    {
        Session session = factory.openSession();

        Query query = session.createQuery("FROM Laptop WHERE color = :color");
        query.setParameter("color", color);
        Collection<Laptop> result = query.list();

        session.close();

        return result;
    }

    @Override
    public Collection<Laptop> readLaptopsByManufacturer(Manufacturer manufacturer)
    {
        Session session = factory.openSession();

        Query query = session.createQuery("FROM Laptop WHERE manufacturer = :manufacturer");
        query.setParameter("manufacturer", manufacturer);
        Collection<Laptop> result = query.list();

        session.close();

        return result;
    }

    @Override
    public Collection<Laptop> readPremiumLaptops()
    {
        Session session = factory.openSession();

        String hql = "FROM Laptop WHERE panel_type = 'IPS' AND resolutionX >= 1920 AND resolutionY >= 1080 AND memory_size >= 8192 AND storage_type = 'SSD'";
        Query query = session.createQuery(hql);
        Collection<Laptop> result = query.list();

        session.close();

        return result;
    }
}
