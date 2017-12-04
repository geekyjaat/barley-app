package com.barley.fs;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileResource {

    private final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public Map getFile(String fileName) {

        StringBuilder result = new StringBuilder("");

        //Get file from resources folder
        ClassLoader classLoader = getClass().getClassLoader();
        Map values = null;
        try {
            values = OBJECT_MAPPER.readValue(classLoader.getResourceAsStream("content/" + fileName), HashMap.class);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return values;
    }
}
