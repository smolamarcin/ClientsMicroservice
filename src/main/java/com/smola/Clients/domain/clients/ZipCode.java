package com.smola.Clients.domain.clients;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public
class ZipCode {
    private String zipCode;

    ZipCode() {}

    ZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getZipCode() {
        return zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZipCode zipCode1 = (ZipCode) o;
        return Objects.equals(zipCode, zipCode1.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipCode);
    }
}
