package com.smola.Clients.domain.clients;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "Address")
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private ZipCode zipCode;
    private String streetName;
    private Integer houseNumber;
    private String city;

    public Address(ZipCode zipCode, String streetName, Integer houseNumber, String city) {
        this.zipCode = zipCode;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.city = city;
    }

    Address() {
    }

    public Address(String streetName) {
        this.streetName = streetName;
    }

    public Long getId() {
        return id;
    }

    public String getStreetName() {
        return streetName;
    }

    public ZipCode getZipCode() {
        return zipCode;
    }

    public void setZipCode(ZipCode zipCode) {
        this.zipCode = zipCode;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(streetName, address.streetName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(streetName);
    }
}
