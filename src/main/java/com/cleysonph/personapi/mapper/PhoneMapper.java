package com.cleysonph.personapi.mapper;

import com.cleysonph.personapi.dto.request.PhoneDTO;
import com.cleysonph.personapi.entity.Phone;

public class PhoneMapper {

    public static Phone toModel(PhoneDTO phoneDTO) {
        return Phone
                .builder()
                .type(phoneDTO.getType())
                .number(phoneDTO.getNumber())
                .build();
    }

    public static PhoneDTO toDTO(Phone phone) {
        // TODO
        return null;
    }

}
