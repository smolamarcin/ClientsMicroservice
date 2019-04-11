package com.smola.Clients.domain.clients;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "Address")
@Table(name = "address")
@JsonSerialize(using = AddressSerializer.class)
@JsonDeserialize(using = AddressDeserializer.class)
class Address extends BaseEntity {
    @Embedded
    private ZipCode zipCode;
    private String streetName;
    private Integer houseNumber;
    private String city;

    Address(ZipCode zipCode, String streetName, Integer houseNumber, String city) {
        this.zipCode = zipCode;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.city = city;
    }

    Address() {
    }

    Address(String streetName) {
        this.streetName = streetName;
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

}
