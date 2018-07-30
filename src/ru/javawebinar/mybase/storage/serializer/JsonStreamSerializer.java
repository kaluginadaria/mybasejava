package ru.javawebinar.mybase.storage.serializer;

import ru.javawebinar.mybase.model.Resume;
import ru.javawebinar.mybase.util.JsonParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class JsonStreamSerializer implements StreamSerializer {
    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try(Writer writer = new OutputStreamWriter(os, StandardCharsets.UTF_8)){
            JsonParser.write(r,writer);
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try(Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)){
            return JsonParser.read(reader, Resume.class);
        }
    }
}
