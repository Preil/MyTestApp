package com.eurochemix.webapp;

import com.eurochemix.webapp.Storage.IStorage;
import com.eurochemix.webapp.model.ContactType;
import com.eurochemix.webapp.model.Resume;
import com.eurochemix.webapp.model.SectionType;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Ilya on 22.02.2016.
 */
abstract public class AbstractStorageTest {
    private Resume R1, R2, R3;

    protected IStorage storage;


    @Before
    public void before() throws IOException {
        R1 = new Resume("Полное имя1", "Локация1", "www-ленинрад-спб.ру");
        R1.addContact(ContactType.MAIL, "mailme.gmail.com");
        R1.addContact(ContactType.PHONE, "777-77-77");
        R1.addObjective("Objective1");
        R1.addMultiTextSection(SectionType.ACHIVEMENT, "R1_Achievment1", "R1_Achievment2");
        R1.addMultiTextSection(SectionType.QUALIFICATIONS, "Java", "SQL");

//        R1.addOrganizationSection(SectionType.EXPIRIENCE,
//                new Organization("Organization11",null,
//                        new Organization.Period(LocalDate.of(2005, Month.JANUARY, 1),Organization.Period.NOW,"position1", "content1"),
//                        new Organization.Period(2001, Month.MARCH, 2005, Month.JANUARY,"position2", "content2")),
//                new Link("Organization12","http://Organization12.ru"));



        R2 = new Resume("Полное имя2", "Локация2", "www.yandex.ua");
        R2.addContact(ContactType.MAIL, "mailme@yandex.com");
        R2.addContact(ContactType.SKYPE, "preil200");
        R2.addObjective("Objective2");
        R2.addMultiTextSection(SectionType.ACHIVEMENT, "R2_Achievment1", "R2_Achievment2");
        R2.addMultiTextSection(SectionType.QUALIFICATIONS, "PHP", "MySQL");

        R3 = new Resume("Полное имя3", "Локация3", "www.google.ру");
        R3.addContact(ContactType.MAIL, "mailme@hotbox.com");
        R3.addContact(ContactType.PHONE, "222-55-11");
        R3.addObjective("Objective3");
        R3.addMultiTextSection(SectionType.ACHIVEMENT, "R3_Achievment1", "R3_Achievment2");
        R3.addMultiTextSection(SectionType.QUALIFICATIONS, "Scala", "PostgreSQL");

        storage.clear();
        storage.save(R2);
        storage.save(R1);
        storage.save(R3);

        //TODO add experience and education


//        System.out.println(R1.getLocation());
    }

    @Test
    public void testClear() throws Exception {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void testSave() throws Exception {

    }

    @Test
    public void testUpdate() throws Exception {
        R2.setFullName("Updated N2");
        storage.update(R2);
        assertEquals(R2, storage.load(R2.getUuid()));


    }

    @Test
    public void testLoad() throws Exception {
        assertEquals(R1, storage.load(R1.getUuid()));
        assertEquals(R2, storage.load(R2.getUuid()));
        assertEquals(R3, storage.load(R3.getUuid()));

        System.out.println(storage.load(R1.getUuid()));
        System.out.println(storage.load(R2.getUuid()));
        System.out.println(storage.load(R3.getUuid()));

    }

    @Test
    public void testDelete() throws Exception {
        storage.delete(R1.getUuid());
        assertEquals(2, storage.size());
    }

    @Test
    public void testGetAllSorted() throws Exception {
        List<Resume> list = Arrays.asList(R1, R2, R3);
        Collections.sort(list, new Comparator<Resume>() {
            @Override
            public int compare(Resume o1, Resume o2) {
                int cmp = o1.getFullName().compareTo(o2.getFullName());
                if (cmp != 0) return cmp;
                return o1.getFullName().compareTo(o2.getFullName());
            }
        });
        assertEquals(list, storage.getAllSorted());
        System.out.println(list);
        System.out.println(storage.getAllSorted());
    }




    @Test
    public void testSize() throws Exception {
        assertEquals(3, storage.size());
    }
//    @Test (expected = WebAppException.class){
//        public void testDeleteMissed() throw Exception{
//            storage.delete("dummy");
//        }
//    }

}