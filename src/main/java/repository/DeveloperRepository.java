package repository;

import model.Developer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeveloperRepository implements GenericRepository<Developer> {

    public Developer getById(Long id) throws IOException {
        return null;
    }

    public ArrayList<Developer> getAll() throws IOException {
        return null;
    }

    public List<Developer> create(Developer developer) throws IOException {
        return null;
    }

    public void delete(Long id) {

    }

    public List<Developer> update(Developer developer) {
        return null;
    }
}
