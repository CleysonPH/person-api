package com.cleysonph.personapi.service;

import java.util.List;
import java.util.stream.Collectors;

import com.cleysonph.personapi.dto.request.PersonDTO;
import com.cleysonph.personapi.dto.response.MessageResponseDTO;
import com.cleysonph.personapi.entity.Person;
import com.cleysonph.personapi.exception.PersonNotFoundException;
import com.cleysonph.personapi.repository.PersonRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private PersonRepository personRepository;

    private ModelMapper modelMapper;

    @Autowired
    public PersonService(PersonRepository personRepository, ModelMapper modelMapper) {
        this.personRepository = personRepository;
        this.modelMapper = modelMapper;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO) {
        Person personToSave = modelMapper.map(personDTO, Person.class);

        Person savedPerson = personRepository.save(personToSave);

        return createMessageResponse(savedPerson.getId(), "Created person with ID");
    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();

        return allPeople.stream()
                .map(person -> modelMapper.map(person, PersonDTO.class))
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = verifyIfExists(id);

        return modelMapper.map(person, PersonDTO.class);
    }

    public void delete(Long id) throws PersonNotFoundException {
        verifyIfExists(id);

        personRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExists(id);

        Person personToUpdate = modelMapper.map(personDTO, Person.class);
        personToUpdate.setId(id);

        Person updatedPerson = personRepository.save(personToUpdate);

        return createMessageResponse(updatedPerson.getId(), "Updated person with ID");
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
            .orElseThrow(() -> new PersonNotFoundException(id));
    }

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
            .builder()
            .message(message + " " + id)
            .build();
    }

}
