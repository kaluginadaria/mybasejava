package ru.javawebinar.mybase.storage;

import org.junit.Assert;
import org.junit.Test;
import ru.javawebinar.mybase.exception.StorageException;
import ru.javawebinar.mybase.model.Resume;

public class AbstractArrayStorageTest extends AbstractStorageTest {
    public AbstractArrayStorageTest(Storage storage) {
        super(storage);

    }


    @Test(expected = StorageException.class)
    public void saveOverflow() throws Exception {
        try {
            for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("Name" + i));
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        storage.save(new Resume("Overflow"));
    }
}
