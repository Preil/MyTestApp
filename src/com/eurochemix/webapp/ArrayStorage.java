package com.eurochemix.webapp;

import com.eurochemix.webapp.model.Resume;

import java.util.Collection;

/**
 * Created by Ilya on 14.02.2016.
 */
public class ArrayStorage implements IStorage{
    private Resume[] array = new Resume[100];

    @Override
    public void clear() {
//        array[i]=null;
    }

    @Override
    public void save(Resume r) {

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
        for (int i = 0; i<= array.length; i++){
            
            if ( boolean b = array[i] == null;)
        }
        
        return ;
    }
}
