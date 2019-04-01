package com.smola.Clients.domain.clients.dto;

import com.smola.Clients.domain.clients.ZipCode;

class ZipCodeConverter {
    public static ZipCodeDto toDto(ZipCode zipCode){
        ZipCodeDto zipCodeDto = new ZipCodeDto(zipCode.getZipCode());
        return zipCodeDto;
    }
}
