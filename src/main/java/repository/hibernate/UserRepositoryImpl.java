package repository.hibernate;

import model.User;
import repository.UserRepository;

import java.util.List;

/**
 * @author Nikita Gvardeev
 * 07.12.2021
 */
public class UserRepositoryImpl implements UserRepository {

    @Override
    public User getByUserName(String name) {
        return null;
    }

    @Override
    public User getById(Long id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User save(User entity) {
        return null;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
