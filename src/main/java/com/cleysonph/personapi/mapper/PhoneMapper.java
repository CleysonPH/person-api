package com.cleysonph.personapi.mapper;

import com.cleysonph.personapi.dto.request.PhoneDTO;
import com.cleysonph.personapi.entity.Phone;

public class PhoneMapper {

    public static Phone toModel(PhoneDTO phoneDTO) {
        return Phone
            .builder()
            .id(phoneDTO.getId())
            .type(phoneDTO.getType())
            .number(phoneDTO.getNumber())
            .build();
    }

    public static PhoneDTO toDTO(Phone phone) {
        return PhoneDTO
            .builder()
            .id(phone.getId())
            .type(phone.getType())
            .number(phone.getNumber())
            .build();
    }

}
