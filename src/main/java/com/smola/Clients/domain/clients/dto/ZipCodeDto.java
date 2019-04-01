package com.smola.Clients.domain.clients.dto;

import java.util.Objects;

public final class ZipCodeDto {
    private final String zipCode;

    public ZipCodeDto(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getZipCode() {
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
