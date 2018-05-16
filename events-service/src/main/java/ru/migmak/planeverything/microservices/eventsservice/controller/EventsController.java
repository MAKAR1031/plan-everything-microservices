package ru.migmak.planeverything.microservices.eventsservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.migmak.planeverything.microservices.eventsservice.ro.TaskEventRo;
import ru.migmak.planeverything.microservices.eventsservice.service.EventsService;

@RestController
@RequestMapping("/taskEvents")
@RequiredArgsConstructor
public class EventsController {

    private final EventsService service;

    @PostMapping("/create")
    public void onCreate(@RequestBody TaskEventRo event) {
        service.createEvent(event.toDomain());
    }

    @PostMapping("/update")
    public void onUpdate(@RequestBody TaskEventRo event) {
        service.updateEvent(event.toDomain());
    }

    @PostMapping("/finish")
    public void onFinish(@RequestBody TaskEventRo event) {
        service.finishEvent(event.toDomain());
    }
}
