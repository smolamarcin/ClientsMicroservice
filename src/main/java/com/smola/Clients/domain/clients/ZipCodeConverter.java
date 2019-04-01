package com.smola.Clients.domain.clients;

import com.smola.Clients.domain.clients.ZipCode;
import com.smola.Clients.domain.clients.dto.ZipCodeDto;

class ZipCodeConverter {
    public static ZipCodeDto toDto(ZipCode zipCode){
        ZipCodeDto zipCodeDto = new ZipCodeDto(zipCode.getZipCode());
        return zipCodeDto;
    }
}
