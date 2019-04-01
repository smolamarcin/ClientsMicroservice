package com.smola.Clients.domain.clients.dto;

import com.smola.Clients.domain.clients.ZipCode;

import java.util.Objects;

class AddressDto {
    private ZipCodeDto zipCode;
    private String streetName;
    private Integer houseNumber;
    private String city;

    public void setZipCode(ZipCodeDto zipCode) {
        this.zipCode = zipCode;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public ZipCodeDto getZipCode() {
        return zipCode;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public String getCity() {
        return city;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressDto that = (AddressDto) o;
        return Objects.equals(zipCode, that.zipCode) &&
                Objects.equals(streetName, that.streetName) &&
                Objects.equals(houseNumber, that.houseNumber) &&
                Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipCode, streetName, houseNumber, city);
    }
}
