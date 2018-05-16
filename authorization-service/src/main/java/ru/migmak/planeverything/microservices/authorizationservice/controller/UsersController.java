package ru.migmak.planeverything.microservices.authorizationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.migmak.planeverything.microservices.authorizationservice.ro.AccountRegistration;
import ru.migmak.planeverything.microservices.authorizationservice.service.AccountsService;

import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final AccountsService service;

    @Autowired
    public UsersController(AccountsService service) {
        this.service = service;
    }

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public Principal getUser(Principal principal) {
        return principal;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody AccountRegistration registration) {
        service.register(registration.toAccount());
    }
}
