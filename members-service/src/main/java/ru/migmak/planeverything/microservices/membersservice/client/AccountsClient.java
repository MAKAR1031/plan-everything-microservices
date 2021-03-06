package ru.migmak.planeverything.microservices.membersservice.client;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import ru.migmak.planeverything.microservices.membersservice.client.model.Account;

@FeignClient(value = "auth", path = "/accounts")
public interface AccountsClient {

    @GetMapping(path = "/search/me")
    Account findMe();
}
