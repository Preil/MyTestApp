package com.eurochemix.webapp.Storage;

import com.eurochemix.webapp.model.Resume;

import java.io.IOException;
import java.util.Collection;

/**
 * Created by Ilya on 14.02.2016.
 */
public interface IStorage {
    void clear();

    void save(Resume r) throws IOException;

    void update(Resume r);

    Resume load(String uuid);

    void delete(String uuid);

    Collection<Resume> getAllSorted();

    int size();

}
