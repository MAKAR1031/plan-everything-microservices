package ru.migmak.planeverything.microservices.eventsservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.migmak.planeverything.microservices.eventsservice.domain.EventType;
import ru.migmak.planeverything.microservices.eventsservice.domain.TaskEvent;
import ru.migmak.planeverything.microservices.eventsservice.domain.enums.EventTypeCode;
import ru.migmak.planeverything.microservices.eventsservice.exception.ServiceException;
import ru.migmak.planeverything.microservices.eventsservice.repository.EventTypeRepository;
import ru.migmak.planeverything.microservices.eventsservice.repository.TaskEventRepository;

import java.util.Date;

@Service
@Transactional
@RequiredArgsConstructor
public class EventsService {

    private final TaskEventRepository eventRepository;
    private final EventTypeRepository typeRepository;

    public void createEvent(TaskEvent event) {
        saveEvent(event, EventTypeCode.CREATE);
    }

    public void updateEvent(TaskEvent event) {
        saveEvent(event, EventTypeCode.UPDATE);
    }

    public void finishEvent(TaskEvent event) {
        saveEvent(event, EventTypeCode.FINISH);
    }

    private void saveEvent(TaskEvent event, EventTypeCode type) {
        EventType eventType = typeRepository.findByCode(type.name())
                .orElseThrow(() -> new ServiceException(String.format("Event type '%s' not found", type.name())));
        event.setTime(new Date());
        event.setType(eventType);
        eventRepository.save(event);
    }
}
