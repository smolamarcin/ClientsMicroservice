package com.smola.Clients.domain.clients.dto;


import java.util.Objects;

public final class AddressDto {
    private ZipCodeDto zipCode;
    private String streetName;
    private Integer houseNumber;
    private String city;

    public AddressDto(ZipCodeDto zipCode, String streetName, Integer houseNumber, String city) {
        this.zipCode = zipCode;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.city = city;
    }

    public ZipCodeDto getZipCode() {
        return new ZipCodeDto(zipCode.getZipCode());
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
