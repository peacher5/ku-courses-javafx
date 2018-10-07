package me.iampeach.kucourses.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DatabaseUtils {

    public static String getCourseListJSON() {
        String json = null;
        try {
            json = readFile("/database/courses_60.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    private static String readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(DatabaseUtils.class.getResource(path).getPath()));
        return new String(encoded, StandardCharsets.UTF_8);
    }
}
