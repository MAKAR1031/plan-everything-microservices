package ru.migmak.planeverything.microservices.authorizationservice.service;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.migmak.planeverything.microservices.authorizationservice.domain.Account;
import ru.migmak.planeverything.microservices.authorizationservice.domain.AccountRole;
import ru.migmak.planeverything.microservices.authorizationservice.repository.AccountRepository;
import ru.migmak.planeverything.microservices.authorizationservice.repository.AccountRoleRepository;

import static ru.migmak.planeverything.microservices.authorizationservice.domain.enums.AccountRoleCode.USER;

@Service
@Transactional
public class AccountsService {
    private final AccountRepository accountRepository;
    private final AccountRoleRepository roleRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public AccountsService(
            AccountRepository accountRepository,
            AccountRoleRepository roleRepository,
            PasswordEncoder encoder
    ) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    public void register(Account account) {
        AccountRole role = roleRepository.findByCode(USER.name())
                .orElseThrow(() -> new ServiceException("Account role 'USER' not found"));
        account.setRole(role);
        account.setBlocked(false);
        account.setPassword(encoder.encode(account.getPassword()));
        accountRepository.save(account);
    }
}
