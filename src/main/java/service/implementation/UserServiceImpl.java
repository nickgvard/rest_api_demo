package service.implementation;

import entity.UserEntity;
import repository.hibernate.HibernateUserRepositoryImpl;
import service.UserService;

import java.util.List;

/**
 * @author Nikita Gvardeev
 * 07.12.2021
 */
public class UserServiceImpl implements UserService {

    private final HibernateUserRepositoryImpl userRepository;

    public UserServiceImpl() {
        userRepository = new HibernateUserRepositoryImpl();
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public UserEntity getById(Long id) {
        return userRepository.getById(id);
    }

    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public UserEntity update(UserEntity userEntity) {
        return userRepository.update(userEntity);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
