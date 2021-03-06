package ru.migmak.planeverything.microservices.authorizationservice.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.migmak.planeverything.microservices.authorizationservice.domain.Account;
import ru.migmak.planeverything.microservices.authorizationservice.domain.AccountRole;
import ru.migmak.planeverything.microservices.authorizationservice.repository.AccountRepository;
import ru.migmak.planeverything.microservices.authorizationservice.repository.AccountRoleRepository;

import static ru.migmak.planeverything.microservices.authorizationservice.domain.enums.AccountRoleCode.ADMIN;

@Component
public class AdministratorAccountCreator implements CommandLineRunner {

    @Value("${security.admin.fullName}")
    private String adminFullName;
    @Value("${security.admin.email}")
    private String adminEmail;

    private final AccountRepository accountRepository;
    private final AccountRoleRepository roleRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public AdministratorAccountCreator(
            AccountRepository accountRepository,
            AccountRoleRepository roleRepository,
            PasswordEncoder encoder
    ) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    @Override
    @Transactional
    public void run(String... args) {
        if (accountRepository.count() >= 1) {
            return;
        }
        String password = "admin";
        System.out.println("\n\n\n==============================");
        System.out.println("Administrator password: " + password);
        System.out.println("==============================\n\n\n");
        Account account = new Account();
        account.setLogin("admin");
        account.setPassword(encoder.encode(password));
        account.setBlocked(false);
        account.setEmail(adminEmail);
        account.setFullName(adminFullName);
        AccountRole accountRole = roleRepository.findByCode(ADMIN.name()).orElseThrow(IllegalStateException::new);
        account.setRole(accountRole);
        accountRepository.save(account);
    }
}
