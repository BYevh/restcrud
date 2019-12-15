package repository;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public interface GenericRepository<T> {
    T getById(Long id) throws IOException;

    List<T> getAll() throws IOException;

    List<T> create(T t) throws IOException;

    void delete(T t);

    List<T> update(T t);
}