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

    private String streetName;

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
