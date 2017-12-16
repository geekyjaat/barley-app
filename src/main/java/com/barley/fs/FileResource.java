package com.barley.fs;

import com.fasterxml.jackson.databind.ObjectMapper;
import spark.utils.IOUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileResource {

    private final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public Map getJsonFromFile(String fileName) {

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

    public String getStringFromFile(String fileName) {
        //Get file from resources folder
        ClassLoader classLoader = getClass().getClassLoader();
        String value = null;
        try {
            value = IOUtils.toString(classLoader.getResourceAsStream("content/" + fileName));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return value;
    }
}
