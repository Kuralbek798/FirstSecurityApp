package com.example.firstsecurityapp.services;

import com.example.firstsecurityapp.models.Person;
import com.example.firstsecurityapp.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class PersonValidateService {

private final PeopleRepository peopleRepository;

    @Autowired
    public PersonValidateService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }


    public Optional<Person> findByNamePostMethod(String username){

        return peopleRepository.findByUsername(username);
    }
}
