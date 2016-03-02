package com.eurochemix.webapp;

import com.eurochemix.webapp.model.ContactType;
import com.eurochemix.webapp.model.Resume;

import java.io.*;
import java.util.Map;

/**
 * Created by Ilya on 28.02.2016.
 */
public class DataStreamFileStorage extends FileStorage {

    public DataStreamFileStorage(String path){
        super(path);
    }

    protected void write(File file, Resume r) throws WebAppException { // создаем потоковы объект для записи данных в файл, оборачиваем объект классом для записи примитивных данных
        try (FileOutputStream fos = new FileOutputStream(file); DataOutputStream dos = new DataOutputStream(fos)) {
            dos.writeUTF(r.getFullName());// записываем данные ФИО объекта резюме в файл
            dos.writeUTF(r.getLocation());
            dos.writeUTF(r.getHomePage());
            Map<ContactType, String> contacts = r.getContacts(); // создаем мапу контактов в которую помещаем мапу контактов объекта резюму
            dos.writeInt(contacts.size()); // записываем в файл количество контактов, объекта резюме.
            for (Map.Entry<ContactType, String> entry : r.getContacts().entrySet()) { // для каждой записи (entry) мапы контактов
                dos.writeInt(entry.getKey().ordinal()); // записываем порядковый номер тип контакта (ключ мапы)
                dos.writeUTF(entry.getValue()); // и значение контакта (значение соотвествующее ключу)
            }
//            for(Map.Entry<SectionType, Section> entry:r.getSections().entrySet()){
//                dos.writeInt(entry.getKey().ordinal());
//                dos.writeUTF(entry.getValue());
//            }
        } catch (IOException e) {
            throw new WebAppException("Couldn't write file " + file.getAbsolutePath(), r, e);
        }
    }

    protected Resume read(File file) {
        Resume r = new Resume();
        try (InputStream is = new FileInputStream(file); DataInputStream dis = new DataInputStream(is)) {
            r.setFullName(dis.readUTF());
            r.setLocation(dis.readUTF());
            r.setHomePage(dis.readUTF());
            int contactSize = dis.readInt();

            for (int i = 0; i < contactSize; i++) {
                r.addContact((ContactType.VALUES[dis.readInt()]), dis.readUTF());
            }

        } catch (IOException e) {
            throw new WebAppException("Can't read file " + file.getAbsolutePath(), e);
        }
        return null;
    }



}
