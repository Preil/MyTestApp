package com.eurochemix.webapp;

import com.eurochemix.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Ilya on 14.02.2016.
 */
public class ArrayStorage extends AbstractStorage<Integer> {
    public static final int LIMIT = 100;

    private Resume[] array = new Resume[LIMIT];
    private int size = 0;

    @Override
    protected void doClear() {
        Arrays.fill(array, null);
        size = 0;
    }


    @Override
    protected boolean exist(Integer idx) {
        return idx != -1;
    }


    @Override
    protected void doSave(Integer idx, Resume r) {
        array[size++] = r;
    }

    @Override
    protected void doUpdate(Integer idx, Resume r) {
        if (idx == -1) throw new WebAppException("Resume " + r.getUuid() + " not exist", r);
        array[idx] = r;
    }

    @Override
    protected Resume doLoad(Integer idx) {
        return array[idx];
    }

    @Override
    protected void doDelete(Integer idx) {
        int numMove = size - idx - 1;
        if (numMove < 0) System.arraycopy(array, idx + 1, array, idx, numMove);
        array[--size] = null;
    }

    @Override
    protected List<Resume> doGetAll() {
        return Arrays.asList(Arrays.copyOf(array, size));
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    protected Integer getContext(String uuid) {
        for (int i = 0; i < LIMIT; i++) {
            if (array[i] != null) {
                if (array[i].getUuid().equals(uuid)) return i;

            }
        }
        return -1;
    }
}
