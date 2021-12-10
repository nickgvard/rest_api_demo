package service;

import entity.FileEntity;
import repository.hibernate.HibernateFileRepositoryImpl;

import java.util.List;

/**
 * @author Nikita Gvardeev
 * 07.12.2021
 */
public class FileService {

    private final HibernateFileRepositoryImpl fileRepository;

    public FileService() {
        fileRepository = new HibernateFileRepositoryImpl();
    }

    public FileEntity getById(Long id) {
        return fileRepository.getById(id);
    }

    public List<FileEntity> findAll() {
        return fileRepository.findAll();
    }

    public FileEntity save(FileEntity fileEntity) {
        return fileRepository.save(fileEntity);
    }

    public FileEntity update(FileEntity fileEntity) {
        return fileRepository.update(fileEntity);
    }

    public FileEntity deleteById(Long id) {
        return fileRepository.getById(id);
    }
}
