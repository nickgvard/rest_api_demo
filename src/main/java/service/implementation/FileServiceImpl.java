package service.implementation;

import entity.FileEntity;
import repository.hibernate.HibernateFileRepositoryImpl;
import service.FileService;

import java.util.List;

/**
 * @author Nikita Gvardeev
 * 07.12.2021
 */
public class FileServiceImpl implements FileService {

    private final HibernateFileRepositoryImpl fileRepository;

    public FileServiceImpl() {
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

    public void deleteById(Long id) {
        fileRepository.deleteById(id);
    }
}