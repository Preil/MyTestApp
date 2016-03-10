package com.eurochemix.webapp;

import com.eurochemix.webapp.model.Resume;
import com.eurochemix.webapp.util.JsonParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Created by Ilya on 09.03.2016.
 */
public class JsonFileStorage extends FileStorage {

    public JsonFileStorage(String path) {
        super(path);

    }

    @Override
    protected void write(OutputStream os, Resume resume) throws IOException {
        try (Writer w = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            JsonParser.write(resume, w);
        }
    }

    @Override
    protected Resume read(InputStream is) throws IOException {
        try (Reader r = new InputStreamReader(is, StandardCharsets.UTF_8))  {
            return JsonParser.read(r,Resume.class);
        }
    }
}
