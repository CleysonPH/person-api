package com.cleysonph.personapi.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.cleysonph.personapi.dto.request.PersonDTO;
import com.cleysonph.personapi.entity.Person;
import com.cleysonph.personapi.entity.Phone;

public class PersonMapper {

    public static Person toModel(PersonDTO personDTO) {
        List<Phone> phones = personDTO
            .getPhones()
            .stream()
            .map(
                phoneDTO -> PhoneMapper
                    .toModel(phoneDTO))
                    .collect(Collectors.toList()
            );

        LocalDate birthDate = null;
        if (personDTO.getBirthDate() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            birthDate = LocalDate.parse(personDTO.getBirthDate(), formatter);
        }

        return Person
                .builder()
                .firstName(personDTO.getFirstName())
                .lastName(personDTO.getLastName())
                .cpf(personDTO.getCpf())
                .birthDate(birthDate)
                .phones(phones)
                .build();
    }

    public static PersonDTO toDTO(Person person) {
        // TODO
        return null;
    }

}
