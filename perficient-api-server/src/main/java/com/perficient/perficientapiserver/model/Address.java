package com.perficient.perficientapiserver.model;

import java.util.UUID;

public class Address {
    private UUID address_id;
    private String street;
    private String suite;
    private String city;
    private String region;
    private String postal;
    private String countryCode;

    public Address(){

    }

    public UUID getAddress_id() {
        return address_id;
    }

    public void setAddress_id(UUID address_id) {
        this.address_id = address_id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Address(UUID address_id, String street, String suite, String city, String region, String postal, String countryCode) {
        this.address_id = address_id;
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.region = region;
        this.postal = postal;
        this.countryCode = countryCode;
    }

    public String toString(){
        return "" + getAddress_id() + " " + getStreet() + "" + getSuite() + " " + getCity() + " "+ getRegion() + " " +getPostal() + " "+ getCountryCode();
    }
}
