package com.eurochemix.webapp;

import com.eurochemix.webapp.model.Resume;

import java.util.Collection;

/**
 * Created by Ilya on 14.02.2016.
 */
public interface IStorage {
    void clear();

    void save(Resume r);

    void update(Resume r);

    Resume load(String uuid);

    void delete(String uuid);

    Collection<Resume> getAllSorted();

    int size();

}
