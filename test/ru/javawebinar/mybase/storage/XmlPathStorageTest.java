package ru.javawebinar.mybase.storage;

import ru.javawebinar.mybase.storage.serializer.XmlStreamSerializer;

import java.security.PublicKey;

public class XmlPathStorageTest extends AbstractStorageTest {
    public XmlPathStorageTest(){
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new XmlStreamSerializer()));
    }
}
