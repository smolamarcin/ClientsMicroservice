package com.smola.Clients.domain.clients.dto;

import com.smola.Clients.domain.clients.Address;

import java.util.ArrayList;
import java.util.List;

class AddressConverter{
    public static AddressDto toDto(Address address){
        AddressDto addressDto = new AddressDto();
        addressDto.setStreetName(address.getStreetName());
        return addressDto;
    }

    public static List<AddressDto> toDto(List<Address> addresses) {
        List<AddressDto> dtos = new ArrayList<>();
        for (Address address : addresses) {
            AddressDto addressDto = toDto(address);
            dtos.add(addressDto);
        }
        return dtos;
    }

}
