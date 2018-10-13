package me.iampeach.kucourses.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.iampeach.kucourses.models.PassedCourses;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DatabaseUtils {
    private static PassedCourses passedCourses;

    private static final String USER_DATABASE = "user.json";

    private static final String passedCoursesDatabase = "/database/user.json";
    private static final String courseListDatabase = "/database/courses_60.json";

    private DatabaseUtils() { }

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
        File f = new File(USER_DATABASE);

        // create new file if database not exist
        if (!f.exists()) {
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(USER_DATABASE), "utf-8"))) {
                writer.write(getPassedCoursesJson());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Gson gson = new Gson();

        // read file and puts it to Gson
        try {
            String userJson = new String(Files.readAllBytes(Paths.get(USER_DATABASE)));
            passedCourses = gson.fromJson(userJson, PassedCourses.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PassedCourses getPassedCourses() {
        if (passedCourses == null) {
            loadPassedCourses();
        }

        return passedCourses;
    }

    public static void writePassesCourses(PassedCourses passedCourses) {
        try (Writer writer = new FileWriter(USER_DATABASE)) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(passedCourses, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readFile(String path) throws IOException {
        byte[] encoded = DatabaseUtils.class.getResourceAsStream(path).readAllBytes();
        return new String(encoded, StandardCharsets.UTF_8);
    }
}
