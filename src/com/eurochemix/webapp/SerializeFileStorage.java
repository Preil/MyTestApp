package com.eurochemix.webapp;

import com.eurochemix.webapp.model.Resume;

import java.io.*;

/**
 * Created by Ilya on 28.02.2016.
 */
public class SerializeFileStorage extends FileStorage {

    public SerializeFileStorage(String path){
        super(path);
    }

    protected void write(File file, Resume r) throws WebAppException { // создаем потоковы объект для записи данных в файл, оборачиваем объект классом для записи примитивных данных
        try (FileOutputStream fos = new FileOutputStream(file); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(r);
        } catch (IOException e) {
            throw new WebAppException("Couldn't write file " + file.getAbsolutePath(), r, e);
        }
    }

    protected Resume read(File file) {
        Resume r = new Resume();
        try (InputStream is = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(is)) {
            return (Resume)ois.readObject();
        } catch (IOException e) {
            throw new WebAppException("Can't read file " + file.getAbsolutePath(), e);
        } catch (ClassNotFoundException e) {
            throw new WebAppException("Error read resume", e);
        }
    }
}
