package com.eurochemix.webapp;

import com.eurochemix.webapp.model.Resume;

import java.util.Collection;

/**
 * Created by Ilya on 14.02.2016.
 */
public class ArrayStorage implements IStorage {
    public static final int LIMIT = 100;
    private Resume[] array = new Resume[LIMIT];

    @Override
    public void clear() {
//        array[i]=null;
    }

    @Override
    public void save(Resume r) {
        for (int i = 0; i < LIMIT; i++) {
            Resume resume = array[i];
            if (array[i] != null) {
                if (r.equals(resume)) {
                    throw new IllegalStateException("Already present");
                }
            }
        }
        for (int i = 0; i < LIMIT; i++) {
            if (array[i] == null) {
                array[i] = r;
            }
        }


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
//        for (int i = 0; i<= array.length; i++){

//            if ( boolean b = array[i] == null;)
//        }

        return 0;
    }
}
