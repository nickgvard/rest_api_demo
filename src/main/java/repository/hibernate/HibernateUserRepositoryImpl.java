package repository.hibernate;

import entity.UserEntity;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.UserRepository;
import util.HibernateDataBaseAccess;

import java.util.List;

/**
 * @author Nikita Gvardeev
 * 07.12.2021
 */
public class HibernateUserRepositoryImpl implements UserRepository {

    @Override
    public UserEntity getById(Long id) {
        @Cleanup Session session = HibernateDataBaseAccess.instance().dataBaseAccess();
        UserEntity userEntity = session.get(UserEntity.class, id);
        return userEntity;
    }

    @Override
    public List<UserEntity> findAll() {
        @Cleanup Session session = HibernateDataBaseAccess.instance().dataBaseAccess();
        Query<UserEntity> query = session.createQuery("from UserEntity", UserEntity.class);
        List<UserEntity> users = query.getResultList();
        return users;
    }

    @Override
    public UserEntity save(UserEntity entity) {
        @Cleanup Session session = HibernateDataBaseAccess.instance().dataBaseAccess();
        session.beginTransaction();
        Long id = (Long) session.save(entity);
        entity.setId(id);
        session.getTransaction().commit();
        return entity;
    }

    @Override
    public UserEntity update(UserEntity entity) {
        @Cleanup Session session = HibernateDataBaseAccess.instance().dataBaseAccess();
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
        return entity;
    }

    @Override
    public void deleteById(Long id) {
        @Cleanup Session session = HibernateDataBaseAccess.instance().dataBaseAccess();
        session.beginTransaction();
        UserEntity userEntity = UserEntity.builder().id(id).build();
        session.delete(userEntity);
        session.getTransaction().commit();
    }
}