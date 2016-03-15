package com.eurochemix.webapp;

import com.eurochemix.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

/**
 * Created by Ilya on 28.02.2016.
 */
public class DataStreamFileStorage extends FileStorage {

    public DataStreamFileStorage(String path) {
        super(path);
    }

    @Override
    protected void write(OutputStream os, Resume resume) throws IOException { // создаем потоковы объект для записи данных в файл, оборачиваем объект классом для записи примитивных данных
        try (final DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());// записываем данные ФИО объекта резюме в файл
            dos.writeUTF(resume.getLocation());
            dos.writeUTF(resume.getHomePage());

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
                    dos.writeUTF(entry.getValue());
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
                dos.writeUTF(type.name());

                switch (type) {
                    case OBJECTIVE:
                        dos.writeUTF(((TextSection) section).getValue());
                        break;
                    case ACHIVEMENT:
                    case QUALIFICATIONS:
                        // реализация через лямбда:
                        // writeCollection(dos, ((MultiTextSection) section).getValues(), value -> writeString(dos, value));
                        writeCollection(dos, ((MultiTextSection) section).getValues(), dos::writeUTF);
                        break;
                    case EDUCATION:
                    case EXPIRIENCE:
                        writeCollection(dos, ((OrganizationSection) section).getValues(), (org) -> {
                            dos.writeUTF(org.getLink().getName());
                            dos.writeUTF(org.getLink().getUrl());
                            writeCollection(dos, org.getPeriods(), period -> {
                                DataStreamFileStorage.this.writeLocalDate(dos, period.getStartDate());
                                DataStreamFileStorage.this.writeLocalDate(dos, period.getEndDate());
                                dos.writeUTF(period.getPosition());
                                dos.writeUTF(period.getContent());
                            });

                        });
                        break;

                }

            }
        }
    }

    @Override
    protected Resume read(InputStream is) throws IOException {
        Resume r = new Resume();
        try (DataInputStream dis = new DataInputStream(is)) {
            r.setUuid(dis.readUTF());
            r.setFullName(dis.readUTF());
            r.setLocation(dis.readUTF());
            r.setHomePage(dis.readUTF());
            int contactSize = dis.readInt();

            for (int i = 0; i < contactSize; i++) {
                r.addContact((ContactType.VALUES[dis.readInt()]), dis.readUTF());
            }
            final int sectionSize = dis.readInt();

            for (int i = 0; i < sectionSize; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case OBJECTIVE:
                        r.addObjective(dis.readUTF());
                        break;
                    case ACHIVEMENT:
                    case QUALIFICATIONS:
                        r.addSection(sectionType, new MultiTextSection(readList(dis, dis::readUTF)));
                        break;
                    case EDUCATION:
                    case EXPIRIENCE:
                        r.addSection(sectionType,
                                new OrganizationSection(readList(dis, () -> new Organization(new Link(dis.readUTF(), dis.readUTF()),
                                        readList(dis, () -> new Organization.Period(readLocalDate(dis), readLocalDate(dis), dis.readUTF(), dis.readUTF()))))));
                        break;


                }

            }
            return r;
        }

    }

    private void writeLocalDate(DataOutputStream dos, LocalDate ld) throws IOException {
        Objects.requireNonNull(ld, "Local date can not be null, use Period.NOW");
        dos.writeInt(ld.getYear());
        dos.writeUTF(ld.getMonth().name());
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), Month.valueOf(dis.readUTF()), 1);
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
