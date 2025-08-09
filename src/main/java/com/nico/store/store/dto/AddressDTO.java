package com.nico.store.store.dto;

public class AddressDTO {
    private String streetAddress;
    private String city;
    private String country;
    private String zipCode;

    public AddressDTO() {}

    public AddressDTO(String streetAddress, String city, String country, String zipCode) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.country = country;
        this.zipCode = zipCode;
    }

    public String getStreetAddress() { return streetAddress; }
    public void setStreetAddress(String streetAddress) { this.streetAddress = streetAddress; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getZipCode() { return zipCode; }
    public void setZipCode(String zipCode) { this.zipCode = zipCode; }
}
