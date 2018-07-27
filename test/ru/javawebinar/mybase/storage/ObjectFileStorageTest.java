package ru.javawebinar.mybase.storage;

import static ru.javawebinar.mybase.storage.AbstractStorageTest.STORAGE_DIR;

public class ObjectFileStorageTest extends AbstractStorageTest {

    public ObjectFileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamSerializer()));
    }
}
