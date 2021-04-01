package com.cleysonph.personapi.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.cleysonph.personapi.dto.request.PersonDTO;
import com.cleysonph.personapi.dto.request.PhoneDTO;
import com.cleysonph.personapi.entity.Person;
import com.cleysonph.personapi.entity.Phone;

public class PersonMapper {

    public static Person toModel(PersonDTO personDTO) {
        List<Phone> phones = personDTO
            .getPhones()
            .stream()
            .map(PhoneMapper::toModel)
            .collect(Collectors.toList());

        LocalDate birthDate = null;
        if (personDTO.getBirthDate() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            birthDate = LocalDate.parse(personDTO.getBirthDate(), formatter);
        }

        return Person
            .builder()
            .id(personDTO.getId())
            .firstName(personDTO.getFirstName())
            .lastName(personDTO.getLastName())
            .cpf(personDTO.getCpf())
            .birthDate(birthDate)
            .phones(phones)
            .build();
    }

    public static PersonDTO toDTO(Person person) {
        List<PhoneDTO> phoneDTOs = person.getPhones()
            .stream()
            .map(PhoneMapper::toDTO)
            .collect(Collectors.toList());

        String birthDate = null;
        if (person.getBirthDate() != null) {
            birthDate = person.getBirthDate().toString();
        }

        return PersonDTO
            .builder()
            .id(person.getId())
            .firstName(person.getFirstName())
            .lastName(person.getLastName())
            .cpf(person.getCpf())
            .birthDate(birthDate)
            .phones(phoneDTOs)
            .build();
    }

}
