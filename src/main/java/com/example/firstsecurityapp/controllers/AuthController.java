package com.example.firstsecurityapp.controllers;

import com.example.firstsecurityapp.models.Person;
import com.example.firstsecurityapp.services.PersonDetailsService;
import com.example.firstsecurityapp.services.RegistrationService;
import com.example.firstsecurityapp.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {


    private final PersonValidator personValidator;
    private final RegistrationService registrationService;


    @Autowired
    public AuthController(PersonValidator personValidator, RegistrationService registrationService) {

        this.personValidator = personValidator;
        this.registrationService = registrationService;
    }

    @GetMapping("/login")
    public String loginPage(Model model){
        return "auth/login";
       // return "hello";
    }
     @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person") Person person){
        return "auth/registration";
    }
    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("person") @Valid Person person,
                                      BindingResult bindingResult){
        personValidator.validate(person,bindingResult);

        if(bindingResult.hasErrors())
            return "/auth/registration";

        registrationService.register(person);
         return "redirect:/auth/login";
    }


}
