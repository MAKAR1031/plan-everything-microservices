package ru.migmak.planeverything.microservices.projectsservice.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.migmak.planeverything.microservices.projectsservice.client.model.Account;

@FeignClient(value = "auth", path = "/accounts")
public interface AccountsClient {

    @GetMapping(path = "/search/me")
    Account findMe(@RequestParam("projection") String projection);
}
