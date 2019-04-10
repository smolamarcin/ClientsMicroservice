package com.smola.Clients.domain.clients;

import com.smola.Clients.domain.clients.ZipCode;
import com.smola.Clients.domain.clients.dto.ZipCodeDto;

class ZipCodeConverter {
    public static ZipCodeDto toDto(ZipCode zipCode){
        ZipCodeDto zipCodeDto = new ZipCodeDto(zipCode.getZipCode());
        return zipCodeDto;
    }

    public static ZipCode toEntity(ZipCodeDto zipCodeDto) {
        ZipCode zipCode = new ZipCode(zipCodeDto.getZipCode());
        return zipCode;
    }
}
