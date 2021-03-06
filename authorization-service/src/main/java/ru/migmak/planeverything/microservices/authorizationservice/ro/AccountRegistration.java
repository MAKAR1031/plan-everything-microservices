package ru.migmak.planeverything.microservices.authorizationservice.ro;

import lombok.Getter;
import lombok.Setter;
import ru.migmak.planeverything.microservices.authorizationservice.domain.Account;

@Getter
@Setter
public class AccountRegistration {
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String email;

    public Account toAccount() {
        Account account = new Account();
        account.setFullName(lastName.trim() + " " + firstName.trim());
        account.setLogin(login);
        account.setPassword(password);
        account.setEmail(email);
        return account;
    }
}
