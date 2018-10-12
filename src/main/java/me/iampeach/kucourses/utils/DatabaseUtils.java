package me.iampeach.kucourses.utils;

import com.google.gson.Gson;
import me.iampeach.kucourses.models.PassedCourses;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class DatabaseUtils {
    private static PassedCourses passedCourses;

    private static final String passedCoursesDatabase = "/database/user.json";
    private static final String courseListDatabase = "/database/courses_60.json";

    public static String getCourseGroupsJson() {
        String json = null;
        try {
            json = readFile(courseListDatabase);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static String getPassedCoursesJson() {
        String json;
        try {
            json = readFile(passedCoursesDatabase);
        } catch (IOException e) {
            return null;
        }
        return json;
    }

    public static void loadPassedCourses() {
        Gson gson = new Gson();
        passedCourses = gson.fromJson(getPassedCoursesJson(), PassedCourses.class);
    }

    public static PassedCourses getPassedCourses() {
        if (passedCourses == null) {
            loadPassedCourses();
        }

        return passedCourses;
    }

    public static void writePassesCourses(PassedCourses passedCourses) {
        Gson gson = new Gson();
        try {
            gson.toJson(passedCourses, new FileWriter(passedCoursesDatabase));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readFile(String path) throws IOException {
        byte[] encoded = DatabaseUtils.class.getResourceAsStream(path).readAllBytes();
        return new String(encoded, StandardCharsets.UTF_8);
    }
}
