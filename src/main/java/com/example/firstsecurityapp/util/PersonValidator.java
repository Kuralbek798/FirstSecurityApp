package com.example.firstsecurityapp.util;

import com.example.firstsecurityapp.models.Person;
import com.example.firstsecurityapp.services.PersonValidateService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator{

    private final PersonValidateService personValidateService;

    public PersonValidator(PersonValidateService personValidateService) {
        this.personValidateService = personValidateService;
    }




    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o , Errors errors) {
       Person person = (Person)o;

       if(personValidateService.findByNamePostMethod(person.getUsername()).isPresent())
           errors.rejectValue("username", "", "Пользователь с таким именем уже существует");

    }
}
