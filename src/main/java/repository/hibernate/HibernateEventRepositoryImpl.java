package repository.hibernate;

import entity.EventEntity;
import repository.EventRepository;

import java.util.List;

/**
 * @author Nikita Gvardeev 10.12.2021
 * email gvardeev@po-korf.ru
 */
public class HibernateEventRepositoryImpl implements EventRepository {

    @Override
    public EventEntity getById(Long aLong) {
        return null;
    }

    @Override
    public List<EventEntity> findAll() {
        return null;
    }

    @Override
    public EventEntity save(EventEntity entity) {
        return null;
    }

    @Override
    public EventEntity update(EventEntity entity) {
        return null;
    }

    @Override
    public EventEntity deleteById(Long aLong) {
        return null;
    }
}
