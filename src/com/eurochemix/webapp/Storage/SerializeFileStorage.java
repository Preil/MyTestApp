package com.eurochemix.webapp.Storage;

import com.eurochemix.webapp.WebAppException;
import com.eurochemix.webapp.model.Resume;

import java.io.*;

/**
 * Created by Ilya on 28.02.2016.
 */
public class SerializeFileStorage extends FileStorage {

    public SerializeFileStorage(String path) {
        super(path);
    }

    @Override
    protected void write(OutputStream os, Resume r) throws IOException { // создаем потоковы объект для записи данных в файл, оборачиваем объект классом для записи примитивных данных
        try (ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(r);
        }
    }

    @Override
    protected Resume read(InputStream is) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(is)) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new WebAppException("Error read resume", e);
        }
    }
}
