package com.smola.Clients.domain.clients;

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

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public String getCity() {
        return city;
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
