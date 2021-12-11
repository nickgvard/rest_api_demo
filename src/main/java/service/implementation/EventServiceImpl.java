package service.implementation;

import entity.EventEntity;
import repository.hibernate.HibernateEventRepositoryImpl;
import service.EventService;

import java.util.List;

/**
 * @author Nikita Gvardeev 10.12.2021
 * email gvardeev@po-korf.ru
 */
public class EventServiceImpl implements EventService {

    private final HibernateEventRepositoryImpl eventRepository;

    public EventServiceImpl() {
        eventRepository = new HibernateEventRepositoryImpl();
    }

    @Override
    public EventEntity getById(Long id) {
        return eventRepository.getById(id);
    }

    @Override
    public List<EventEntity> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public EventEntity save(EventEntity eventEntity) {
        return eventRepository.save(eventEntity);
    }

    @Override
    public EventEntity update(EventEntity eventEntity) {
        return eventRepository.update(eventEntity);
    }

    @Override
    public EventEntity deleteById(Long id) {
        return eventRepository.deleteById(id);
    }
}
