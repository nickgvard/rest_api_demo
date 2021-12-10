package repository.hibernate;

import entity.FileEntity;
import repository.FileRepository;

import java.util.List;

/**
 * @author Nikita Gvardeev 10.12.2021
 * email gvardeev@po-korf.ru
 */
public class HibernateFileRepositoryImpl implements FileRepository {

    @Override
    public FileEntity getById(Long id) {
        return null;
    }

    @Override
    public List<FileEntity> findAll() {
        return null;
    }

    @Override
    public FileEntity save(FileEntity entity) {
        return null;
    }

    @Override
    public FileEntity update(FileEntity entity) {
        return null;
    }

    @Override
    public FileEntity deleteById(Long id) {
        return null;
    }
}
