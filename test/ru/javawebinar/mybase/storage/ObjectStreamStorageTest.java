package ru.javawebinar.mybase.storage;

public class ObjectStreamStorageTest extends AbstractStorageTest{
    public ObjectStreamStorageTest(Storage storage) {
        super(new ObjectStreamStorage(STORAGE_DIR));
    }
}
