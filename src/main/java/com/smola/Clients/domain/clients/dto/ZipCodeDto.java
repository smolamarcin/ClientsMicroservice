package com.smola.Clients.domain.clients.dto;

import java.util.Objects;

class ZipCodeDto {
    private final String zipCode;

    ZipCodeDto(String zipCode) {
        this.zipCode = zipCode;
    }

    String getZipCode() {
        return zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZipCodeDto that = (ZipCodeDto) o;
        return Objects.equals(zipCode, that.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipCode);
    }
}
