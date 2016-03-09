package com.eurochemix.webapp;

import com.eurochemix.webapp.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Ilya on 28.02.2016.
 */
public class DataStreamFileStorage extends FileStorage {
    private static final String NULL = "null";

    public DataStreamFileStorage(String path) {
        super(path);
    }

    @Override
    protected void write(OutputStream os, Resume resume) throws IOException { // создаем потоковы объект для записи данных в файл, оборачиваем объект классом для записи примитивных данных
        try (final DataOutputStream dos = new DataOutputStream(os)) {
            writeString(dos, resume.getUuid());
            writeString(dos, resume.getFullName());// записываем данные ФИО объекта резюме в файл
            writeString(dos, resume.getLocation());
            writeString(dos, resume.getHomePage());

            Map<ContactType, String> contacts = resume.getContacts(); // создаем коллекцию типа MAP, в которую помещаем контакты объекта резюму
//            dos.writeInt(contacts.size()); // Раньше записывали в файл количество контактов, объекта резюме.
// Теперь это делегировано методу writeCollection, который мы специально создалия для записи коллекций
            // обращаемся к методу writeCollection, который умеет записывать любые коллекции, передаем тип нашей коллекции через параметр
            // ElementWriter (внутренний интерфейс, реализуемый в методе writeCollection)
            writeCollection(dos, contacts.entrySet(), new ElementWriter<Map.Entry<ContactType, String>>() {
                @Override
                // который обращается к методу интерфейса ElementWriter, который мы здесь переопределяем с помощью анонимного класса
                public void write(Map.Entry<ContactType, String> entry) throws IOException {// где явно указываем тип коллекции
                    dos.writeInt(entry.getKey().ordinal()); //  и записываем ее данные (ключ и значение)
                    writeString(dos, entry.getValue());
                }
            });


//            for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) { // для каждой записи (entry) мапы контактов
//                dos.writeInt(entry.getKey().ordinal()); // записываем порядковый номер тип контакта (ключ мапы)
//                writeString(dos, entry.getValue()); // и значение контакта (значение соотвествующее ключу)
//            }
            Map<SectionType, Section> sections = resume.getSections();
            int sectionSize = sections.size();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                SectionType type = entry.getKey();
                Section section = entry.getValue();
                writeString(dos, type.name());
                switch (type) {
                    case OBJECTIVE:
                        writeString(dos, ((TextSection) section).getValue());
                        break;
                    case ACHIVEMENT:
                    case QUALIFICATIONS:
                        // реализация через лямбда:
                        // writeCollection(dos, ((MultiTextSection) section).getValues(), value -> writeString(dos, value));
                        writeCollection(dos, ((MultiTextSection) section).getValues(), new ElementWriter<String>() {
                            @Override
                            public void write(String value) throws IOException {
                                writeString(dos, value);
                            }
                        });
                }
            }
        }
    }

    @Override
    protected Resume read(InputStream is) throws IOException {
        Resume r = new Resume();
        try (DataInputStream dis = new DataInputStream(is)) {
            r.setUuid(readString(dis));
            r.setFullName(readString(dis));
            r.setLocation(readString(dis));
            r.setHomePage(readString(dis));
            int contactSize = dis.readInt();

            for (int i = 0; i < contactSize; i++) {
                r.addContact((ContactType.VALUES[dis.readInt()]), readString(dis));
            }
            final int sectionSize = dis.readInt();

            for (int i = 0; i < sectionSize; i++) {

                SectionType sectionType = SectionType.valueOf(readString(dis));
                switch (sectionType) {
                    case OBJECTIVE:
                        r.addObjective(readString(dis));
                        break;
                    case ACHIVEMENT:
                    case QUALIFICATIONS:
                        r.addSection(sectionType, new MultiTextSection(readList(dis, () -> readString(dis))));
                        break;
                }

            }
            return r;
        }

    }








    private void writeString(DataOutputStream dos, String str) throws IOException { // эти методы созданы для проверки записываемых значений объекта на ноль
        dos.writeUTF(str == null ? NULL : str);
    }

    private String readString(DataInputStream dis) throws IOException { // эти методы созданы для проверки записываемых значений объекта на ноль
        String str = dis.readUTF();
        return str.equals(NULL) ? null : str;
    }

    private interface ElementWriter<T> { // внутренний интерфейс содержащий метод write, типизированный дженериком Т
        void write(T t) throws IOException; // принимающий объект типа Т
    }

    // метод который принимает соллекцию типа Т и реализует метод writer внутреннего интерфейса ElementWriter для типа Т
    private <T> void writeCollection(DataOutputStream dos, Collection<T> collection, ElementWriter<T> writer) throws IOException {
        dos.writeInt(collection.size()); // записываем в файл количество контактов, объекта резюме, записанные в коллекцию.
        for (T item : collection) { // для каждой записи (entry) мапы контактов
            writer.write(item);
        }
    }

    // тоже самое делаем для чтения коллекций - создаем внутренний интерфейс типизированный Т,
    private interface ElementReader<T> {
        T read() throws IOException; // с методом read, который возвращает объект типа Т
    }

    // пишем метод для чтения коллекций, метод принимает объект dis типа DataInputStream и реализует интерфейс ElementReader,
    // типизированный типом Т


    private <T> List<T> readList(DataInputStream dis, ElementReader<T> reader) throws IOException {
        int size = dis.readInt(); // в методе создается коллекция типа ArrayList (с количеством записей size,
        // которое читается из файла через DataInputStream)
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) { // заполняем коллекцию с помощью метода read, который будет реализован -
            list.add(reader.read()); // переопределен через анонимный класс в момент вызова метода чтения колеекций readList
        }// все это действо называется паттерном проектирования "Стратегия", создание объекта через анонимный класс,
        return list; // в Java 1.8 можно заменить использованием лямбда выражений
    }

}
