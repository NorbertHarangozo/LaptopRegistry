package hu.maszeksolutions.LaptopRegistry.dao;

import hu.maszeksolutions.LaptopRegistry.exceptions.LaptopAlreadyExists;
import hu.maszeksolutions.LaptopRegistry.exceptions.LaptopNotFound;
import hu.maszeksolutions.LaptopRegistry.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Laptop> query = builder.createQuery(Laptop.class);
        Root<Laptop> root = query.from(Laptop.class);

        query.select(root);

        Collection<Laptop> result = session.createQuery(query).getResultList();

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
    public Collection<Laptop> readLaptopsByParam(Manufacturer manufacturer, String name, Color color, Double screenSize,
                                                 PanelType panelType, Integer resolutionX, Integer resolutionY, Integer refreshRate,
                                                 String cpu, MemoryType memoryType, Integer memorySize, String gpu,
                                                 StorageType storageType, Integer storageSize, Boolean opticalDrive,
                                                 Integer numberOfUSBPorts, LocalDate manufactureDate)
    {
        Session session = factory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Laptop> query = builder.createQuery(Laptop.class);
        Root<Laptop> root = query.from(Laptop.class);

        List<Predicate> predicates = new ArrayList<>();

        if (manufacturer != null)
        {
            Path<Manufacturer> field = root.get("manufacturer");
            Predicate p = builder.equal(field, manufacturer);
            predicates.add(p);
        }
        if (name != null)
        {
            String nameLike = "%" + name + "%";

            Path<String> field = root.get("name");
            Predicate p = builder.like(field, nameLike);
            predicates.add(p);
        }
        if (color != null)
        {
            Path<Color> field = root.get("color");
            Predicate p = builder.equal(field, color);
            predicates.add(p);
        }
        if (screenSize != null)
        {
            Path<Double> field = root.get("screenSize");
            Predicate p = builder.equal(field, screenSize);
            predicates.add(p);
        }
        if (panelType != null)
        {
            Path<String> field = root.get("panelType");
            Predicate p = builder.equal(field, panelType);
            predicates.add(p);
        }
        if (resolutionX != null)
        {
            Path<Integer> field = root.get("resolutionX");
            Predicate p = builder.greaterThanOrEqualTo(field, resolutionX);
            predicates.add(p);
        }
        if (resolutionY != null)
        {
            Path<Integer> field = root.get("resolutionY");
            Predicate p = builder.greaterThanOrEqualTo(field, resolutionY);
            predicates.add(p);
        }
        if (refreshRate != null)
        {
            Path<Integer> field = root.get("refreshRate");
            Predicate p = builder.greaterThanOrEqualTo(field, refreshRate);
            predicates.add(p);
        }
        if (cpu != null)
        {
            Path<String> field = root.get("cpu");
            Predicate p = builder.equal(field, cpu);
            predicates.add(p);
        }
        if (memoryType != null)
        {
            Path<MemoryType> field = root.get("memoryType");
            Predicate p = builder.equal(field, memoryType);
            predicates.add(p);
        }
        if (memorySize != null)
        {
            Path<Integer> field = root.get("memorySize");
            Predicate p = builder.greaterThanOrEqualTo(field, memorySize);
            predicates.add(p);
        }
        if (gpu != null)
        {
            Path<String> field = root.get("gpu");
            Predicate p = builder.equal(field, gpu);
            predicates.add(p);
        }
        if (storageType != null)
        {
            Path<StorageType> field = root.get("storageType");
            Predicate p = builder.equal(field, storageType);
            predicates.add(p);
        }
        if (storageSize != null)
        {
            Path<Integer> field = root.get("storageSize");
            Predicate p = builder.greaterThanOrEqualTo(field, storageSize);
            predicates.add(p);
        }
        if (opticalDrive != null)
        {
            Path<Boolean> field = root.get("opticalDrive");
            Predicate p = builder.equal(field, opticalDrive);
            predicates.add(p);
        }
        if (numberOfUSBPorts != null)
        {
            Path<Integer> field = root.get("numberOfUSBPorts");
            Predicate p = builder.greaterThanOrEqualTo(field, numberOfUSBPorts);
            predicates.add(p);
        }
        if (manufactureDate != null)
        {
            Path<LocalDate> field = root.get("manufactureDate");
            Predicate p = builder.equal(field, manufactureDate);
            predicates.add(p);
        }

        Predicate finalQuery = builder.and(predicates.toArray(new Predicate[0]));
        query.select(root).where(finalQuery);

        Collection<Laptop> result = session.createQuery(query).getResultList();

        session.close();

        return result;
    }
}
