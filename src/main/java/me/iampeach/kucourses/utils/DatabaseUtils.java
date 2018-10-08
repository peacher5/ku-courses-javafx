package me.iampeach.kucourses.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class DatabaseUtils {

    public static String getCourseGroupsJson() {
        String json = null;
        try {
            json = readFile("/database/courses_60.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static String getPassedCoursesJson() {
        String json;
        try {
            json = readFile("/database/user.json");
        } catch (IOException e) {
            return null;
        }
        return json;
    }

    private static String readFile(String path) throws IOException {
        byte[] encoded = DatabaseUtils.class.getResourceAsStream(path).readAllBytes();
        return new String(encoded, StandardCharsets.UTF_8);
    }
}
