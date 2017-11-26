package com.worldbank.dao;

import com.worldbank.model.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class CountryDaoImpl implements CountryDao {

    // Hold a reference to a Session Factory (since we need one Session Factory for whole Application )
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {

        // Create a Standard Service Registry
        final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    @Override
    public void closeSessionFactory() {
        sessionFactory.close();
    }

    @Override
    public List<Country> listAllCountry() {

        // Open Session
        Session session = sessionFactory.openSession();
        // Create CriteriaBuilder
        CriteriaBuilder builder = session.getCriteriaBuilder();
        // Create CriteriaQuery
        CriteriaQuery<Country> criteria = builder.createQuery(Country.class);
        // Specify criteria root
        criteria.from(Country.class);
        // Execute query
        List<Country> worldCountries = session.createQuery(criteria).getResultList();
        // Close Session
        session.close();
        // Return List of World Countries
        return  worldCountries;
    }
    @Override
    public Country findCountryByCode(String code) {

        // open the session
        Session session = sessionFactory.openSession();
        // Retrieve row by its id
        Country country = session.get(Country.class,code);
        // close the session
        session.close();
        // Return Country obtained by its code
        return country;
    }

    @Override
    public Country findCountryByName(String name) {

        // open the session
        Session session = sessionFactory.openSession();
        // Retrieve row by its id
        Country country = session.get(Country.class,name);
        // close the session
        session.close();
        // Return Country obtained by its code
        return country;
    }

    @Override
    public void addCountry(Country country) {

        // Open a Session
        Session session = sessionFactory.openSession();
        // Begin Transaction
        session.beginTransaction();
        // Use Session to Save Contact
        session.save(country);
        // Commit the Transaction
        session.getTransaction().commit();
        // Close the Session
        session.close();
    }

    @Override
    public void deleteCountry(Country country) {

        // Open a Session
        Session session = sessionFactory.openSession();
        // Begin Transaction
        session.beginTransaction();
        // Use Session to Save Contact
        session.delete(country);
        // Commit the Transaction
        session.getTransaction().commit();
        // Close the Session
        session.close();
    }

    @Override
    public void editCountry(Country country) {

        // Open a Session
        Session session = sessionFactory.openSession();
        // Begin Transaction
        session.beginTransaction();
        // Use Session to Save Contact
        session.update(country);
        // Commit the Transaction
        session.getTransaction().commit();
        // Close the Session
        session.close();
    }
}
