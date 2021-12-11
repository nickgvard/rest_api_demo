package repository.hibernate;

import entity.FileEntity;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.FileRepository;
import util.HibernateDataBaseAccess;

import java.util.List;

/**
 * @author Nikita Gvardeev 10.12.2021
 * email gvardeev@po-korf.ru
 */
public class HibernateFileRepositoryImpl implements FileRepository {

    @Override
    public FileEntity getById(Long id) {
        @Cleanup Session session = HibernateDataBaseAccess.instance().dataBaseAccess();
        FileEntity fileEntity = session.get(FileEntity.class, id);
        return fileEntity;
    }

    @Override
    public List<FileEntity> findAll() {
        @Cleanup Session session = HibernateDataBaseAccess.instance().dataBaseAccess();
        Query<FileEntity> query = session.createQuery("from FileEntity", FileEntity.class);
        List<FileEntity> files = query.getResultList();
        return files;
    }

    @Override
    public FileEntity save(FileEntity entity) {
        @Cleanup Session session = HibernateDataBaseAccess.instance().dataBaseAccess();
        session.beginTransaction();
        Long id = (Long) session.save(entity);
        entity.setId(id);
        session.getTransaction().commit();
        return entity;
    }

    @Override
    public FileEntity update(FileEntity entity) {
        @Cleanup Session session = HibernateDataBaseAccess.instance().dataBaseAccess();
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
        return entity;
    }

    @Override
    public FileEntity deleteById(Long id) {
        @Cleanup Session session = HibernateDataBaseAccess.instance().dataBaseAccess();
        session.beginTransaction();
        FileEntity fileEntity = FileEntity.builder().id(id).build();
        session.delete(fileEntity);
        session.getTransaction().commit();
        return fileEntity;
    }
}