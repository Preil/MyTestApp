package com.eurochemix.webapp;

import com.eurochemix.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ilya on 28.02.2016.
 */
public abstract class FileStorage extends AbstractStorage<File> {
    private File dir;

    public FileStorage(String path) { // Конструктор принимает зачение пути директории
        this.dir = new File(path); // значение пути принимает переменная дир
        if (!dir.isDirectory() || !dir.canWrite()) //выполняется проверка директорию и возможность записи
            throw new IllegalArgumentException("'" + path + "' is not a directory or is not writable");


    }

    @Override
    protected void doClear() {
        File[] files = dir.listFiles(); // создаем массив типа файл - передаем в нее список файлов из нашей директории
        if (files == null) return;//проверяем на нуль
        for (File file : files) { // для перебираем каждый файл и стираем его
            doDelete(file);
        }
    }

    @Override
    protected boolean exist(File file) {
        return file.exists();
    }

    @Override
    protected File getContext(String fileName) { // переопределяем метод гетКонтекст, так чтобы он принимая UUID возвращал
        return new File(dir, fileName); // объект типа файл с именем UUID в директории dir
    }

    @Override
    protected void doSave(File file, Resume r) throws IOException {
        try {
            file.createNewFile(); // создаем файл
        } catch (IOException e) {
            throw new WebAppException("Couldn't create file " + file.getAbsolutePath(), r, e);
        }
        write(file, r); // передаем в метод write имя созданного файла и обїект резюме
    }

    abstract protected void write(File file, Resume r);

    abstract protected Resume read(File file);


    @Override
    protected void doUpdate(File file, Resume r) {
        write(file, r);
    }

    @Override
    protected Resume doLoad(File file) {
        if(exist(file)){
            return read(file);
        }else throw new WebAppException("File "+file.getAbsolutePath()+" does npt exist");
    }

    @Override
    protected void doDelete(File file) {
        if (!file.delete()) throw new WebAppException("File " + file.getAbsolutePath() + " can not be deleted");


    }

    @Override
    protected List<Resume> doGetAll() {

        File[] files = dir.listFiles(); // создаем массив типа файл - передаем в нее список файлов из нашей директории
        List<Resume> listResume = new ArrayList<>(files.length);
        if (files == null) throw new WebAppException("The directory is empty"); //проверяем на нуль-пустая директория
        for (File file : files) { // для перебираем каждый файл и читаем его
            listResume.add(read(file));
        }
        return listResume;
    }

    @Override
    public int size() {
        String[] listF = dir.list();
        if (listF == null) throw new WebAppException("Can't read directory" + dir.getAbsolutePath());
        return listF.length;
    }
}
