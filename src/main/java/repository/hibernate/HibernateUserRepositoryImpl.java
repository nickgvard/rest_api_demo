package repository.hibernate;

import entity.UserEntity;
import repository.UserRepository;

import java.util.List;

/**
 * @author Nikita Gvardeev
 * 07.12.2021
 */
public class HibernateUserRepositoryImpl implements UserRepository {

    @Override
    public UserEntity getById(Long id) {
        return null;
    }

    @Override
    public List<UserEntity> findAll() {
        return null;
    }

    @Override
    public UserEntity save(UserEntity entity) {
        return null;
    }

    @Override
    public UserEntity update(UserEntity entity) {
        return null;
    }

    @Override
    public UserEntity deleteById(Long id) {
        return null;
    }
}
