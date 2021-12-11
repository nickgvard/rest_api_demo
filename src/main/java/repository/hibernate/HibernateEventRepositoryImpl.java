package repository.hibernate;

import entity.EventEntity;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.EventRepository;
import util.HibernateDataBaseAccess;

import java.util.List;

/**
 * @author Nikita Gvardeev 10.12.2021
 * email gvardeev@po-korf.ru
 */
public class HibernateEventRepositoryImpl implements EventRepository {

    @Override
    public EventEntity getById(Long id) {
        @Cleanup Session session = HibernateDataBaseAccess.instance().dataBaseAccess();
        EventEntity eventEntity = session.get(EventEntity.class, id);
        return eventEntity;
    }

    @Override
    public List<EventEntity> findAll() {
        @Cleanup Session session = HibernateDataBaseAccess.instance().dataBaseAccess();
        Query<EventEntity> query = session.createQuery("from EventEntity", EventEntity.class);
        List<EventEntity> events = query.getResultList();
        return events;
    }

    @Override
    public EventEntity save(EventEntity entity) {
        @Cleanup Session session = HibernateDataBaseAccess.instance().dataBaseAccess();
        session.beginTransaction();
        Long id = (Long) session.save(entity);
        entity.setId(id);
        session.getTransaction().commit();
        return entity;
    }

    @Override
    public EventEntity update(EventEntity entity) {
        @Cleanup Session session = HibernateDataBaseAccess.instance().dataBaseAccess();
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
        return entity;
    }

    @Override
    public EventEntity deleteById(Long id) {
        @Cleanup Session session = HibernateDataBaseAccess.instance().dataBaseAccess();
        session.beginTransaction();
        EventEntity eventEntity = EventEntity.builder().id(id).build();
        session.delete(eventEntity);
        session.getTransaction().commit();
        return eventEntity;
    }
}