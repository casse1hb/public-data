package com.worldbank.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Country {
    @Id
    private String code;                    // country code
    private String name;                    // country name
    private Double internetUsers;           // country Internet Users
    private Double adultLiteracyRate;       // country Adult Literacy Rate

    public Country() {}

    public Country(CountryBuilder builder) {
        this.code = builder.code;
        this.name = builder.name;
        this.internetUsers = builder.internetUsers;
        this.adultLiteracyRate = builder.adultLiteracyRate;
    }

    @Override
    public String toString() {
        String nullInternetUser;
        String nullLiteracy;
        String formattedOutput;

        // internetUsers and adultLiteracy Rate both are null
        // display format : ASM    American Samoa       --                   --
        if(internetUsers == null && adultLiteracyRate == null) {
            nullInternetUser = "--";
            nullLiteracy = "--";
            formattedOutput = String.format("%-6s %-40s %9s %20s",
                    code,name,nullInternetUser,nullLiteracy);
        }
        // internetUsers is null and adultLiteracyRate is not null
        // display format :  ARM    Armenia         41.90                  --
        else if(internetUsers == null && adultLiteracyRate != null) {
            nullInternetUser = "--";
            formattedOutput = String.format("%-6s %-40s %9s %20.2f",
                    code,name,nullInternetUser,adultLiteracyRate);
        }
        // internet User is not null and adultLiteracy is null
        // display format : AFG    Afghanistan       5.90                  --
        else if(adultLiteracyRate == null && internetUsers != null) {
            nullLiteracy = "--";
            formattedOutput = String.format("%-6s %-40s %10.2f %19s",
                    code,name,internetUsers,nullLiteracy);
        }
        // internet User and adultLiteracy both are not null
        // dispaly format : AGO    Angola         19.10                70.78
        else {
            formattedOutput = String.format("%-6s %-40s %10.2f %20.2f",
                    code,name,internetUsers,adultLiteracyRate);
        }
        return formattedOutput;
    }

    public String getCode() {return code;}

    public void setCode(String code) {this.code = code;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public Double getInternetUsers() {return internetUsers;}

    public void setInternetUsers(Double internetUsers) {this.internetUsers = internetUsers;}

    public Double getAdultLiteracyRate() {return adultLiteracyRate;}

    public void setAdultLiteracyRate(Double adultLiteracyRate) {this.adultLiteracyRate = adultLiteracyRate;}

    public static class CountryBuilder {

        private String code;
        private String name;
        private Double internetUsers;
        private Double adultLiteracyRate;

        public CountryBuilder(String code,String name) {
            this.code = code;
            this.name = name;
        }

        public CountryBuilder withInternetUsers(Double internetUsers) {
            this.internetUsers = internetUsers;
            return this;
        }

        public CountryBuilder withAdultLiteracyRate(Double adultLiteracyRate){
            this.adultLiteracyRate = adultLiteracyRate;
            return this;
        }

        public Country build( ){
            return new Country(this);
        }
    }
}
