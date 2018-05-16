package ru.migmak.planeverything.microservices.authorizationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.migmak.planeverything.microservices.authorizationservice.ro.AccountRegistration;
import ru.migmak.planeverything.microservices.authorizationservice.service.AccountsService;

import java.security.Principal;

@RestController
@RequestMapping("/accounts")
public class AccountsController {

    private final AccountsService service;

    @Autowired
    public AccountsController(AccountsService service) {
        this.service = service;
    }

    @RequestMapping(value = "/principal", method = RequestMethod.GET)
    public Principal getUser(Principal principal) {
        return principal;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody AccountRegistration registration) {
        service.register(registration.toAccount());
    }

    @RequestMapping(value = "/{id}/lock", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void lock(@PathVariable("id") Long id) {
        service.setBlocked(id, true);
    }

    @RequestMapping(value = "/{id}/unlock", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void unlock(@PathVariable("id") Long id) {
        service.setBlocked(id, false);
    }
}
