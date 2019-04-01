package com.smola.Clients.domain.clients;

import com.smola.Clients.domain.clients.dto.AddressDto;

import java.util.ArrayList;
import java.util.List;

class AddressConverter {
    public static AddressDto toDto(Address address) {
        return new AddressDto(ZipCodeConverter.toDto(address.getZipCode()),
                address.getStreetName(), address.getHouseNumber(), address.getCity());
    }

    static List<AddressDto> toDto(List<Address> addresses) {
        List<AddressDto> dtos = new ArrayList<>();
        for (Address address : addresses) {
            AddressDto addressDto = toDto(address);
            dtos.add(addressDto);
        }
        return dtos;
    }

}
