package com.cleysonph.personapi.service;

import com.cleysonph.personapi.dto.request.PersonDTO;
import com.cleysonph.personapi.dto.response.MessageResponseDTO;
import com.cleysonph.personapi.entity.Person;
import com.cleysonph.personapi.mapper.PersonMapper;
import com.cleysonph.personapi.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO) {
        Person personToSave = PersonMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);

        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + savedPerson.getId())
                .build();
    }

}
