package com.worldbank.dao;

import com.worldbank.model.Country;

import java.util.List;

public interface CountryDao {
    List<Country> listAllCountry();
    Country findCountryByCode(String code);
    Country findCountryByName(String name);
    void addCountry(Country country);
    void deleteCountry(Country country);
    void editCountry(Country country);
    void closeSessionFactory();
}
