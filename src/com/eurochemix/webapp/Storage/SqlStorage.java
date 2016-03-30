package com.eurochemix.webapp.Storage;

import com.eurochemix.webapp.Storage.IStorage;
import com.eurochemix.webapp.model.Resume;

import java.io.IOException;
import java.util.Collection;

/**
 * Created by Ilya on 24.03.2016.
 */
public class SqlStorage implements IStorage {
    @Override
    public void clear() {

    }

    @Override
    public void save(Resume r) throws IOException {

    }

    @Override
    public void update(Resume r) {

    }

    @Override
    public Resume load(String uuid) {
        return null;
    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public Collection<Resume> getAllSorted() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
