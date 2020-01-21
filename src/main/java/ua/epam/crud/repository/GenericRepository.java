package ua.epam.crud.repository;

import java.io.IOException;
import java.util.List;

public interface GenericRepository<T, Long> {
    T getById(Long id) throws IOException;

    List<T> getAll() throws IOException;

    List<T> create(T t) throws IOException;

    void delete(Long id) throws IOException;

    List<T> update(T t);
}