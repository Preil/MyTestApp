package com.eurochemix.webapp;

import com.eurochemix.webapp.model.Contact;
import com.eurochemix.webapp.model.ContactType;
import com.eurochemix.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;


/**
 * Created by Ilya on 17.02.2016.
 */
public class ArrayStorageTest {
    private Resume R1, R2, R3;

    private ArrayStorage storage = new ArrayStorage();


    @Before
    public void before() {
        R1 = new Resume("Полное имя1", "Локация1", "www-ленинрад-спб.ру");
        R1.addContact(new Contact(ContactType.MAIL, "mailme.gmail.com"));
        R1.addContact(new Contact(ContactType.PHONE, "777-77-77"));

        R2 = new Resume("Полное имя2", "Локация2", "www.yandex.ua");
        R2.addContact(new Contact(ContactType.MAIL, "mailme.yandex.com"));
        R2.addContact(new Contact(ContactType.SKYPE, "preil200"));

        R3 = new Resume("Полное имя3", "Локация3", "www.google.ру");
        R3.addContact(new Contact(ContactType.MAIL, "mailme.hotbox.com"));
        R3.addContact(new Contact(ContactType.PHONE, "222-55-11"));

        storage.clear();
        storage.save(R2);
        storage.save(R1);
        storage.save(R3);

//        System.out.println(R1.getLocation());
    }

    @Test
    public void testClear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void testSave() throws Exception {

    }

    @Test
    public void testUpdate() throws Exception {
        R2.setFullName("Updated N2");
        storage.update(R2);
        Assert.assertEquals(R2, storage.load(R2.getUuid()));


    }

    @Test
    public void testLoad() throws Exception {
        Assert.assertEquals(R1, storage.load(R1.getUuid()));
        Assert.assertEquals(R2, storage.load(R2.getUuid()));
        Assert.assertEquals(R3, storage.load(R3.getUuid()));

    }

    @Test
    public void testDelete() throws Exception {
        storage.delete(R1.getUuid());
        Assert.assertEquals(2, storage.size());
    }

    @Test
    public void testGetAllSorted() throws Exception {
        Resume[] src = new Resume[]{R1, R2, R3};
        Arrays.sort(src);
        Assert.assertArrayEquals(src, storage.getAllSorted().toArray());
    }

    @Test
    public void testSize() throws Exception {

        Assert.assertEquals(3, storage.size());

    }


}