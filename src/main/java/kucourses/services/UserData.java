package kucourses.services;

import com.google.gson.Gson;
import kucourses.models.User;

import java.io.IOException;
import java.net.URISyntaxException;

public class UserData {
    private static final String userDataPath = "user.json";
    private static UserData instance;
    private User user;

    private UserData() {
        String userDataJson;
        try {
            userDataJson = FileUtils.readFile(userDataPath);
        } catch (IOException | URISyntaxException e) {
            return;
        }

        Gson gson = new Gson();
        user = gson.fromJson(userDataJson, User.class);
    }

    public static UserData getInstance() {
        if (instance == null)
            instance = new UserData();
        return instance;
    }

    public static boolean isExists() {
        try {
            return FileUtils.isFileExists(userDataPath);
        } catch (URISyntaxException e) {
            return false;
        }
    }

    public void initData(String planName) {
        user = new User(planName, new String[0]);
    }

    public User getData() {
        return user;
    }

    public void save() {
        User user = new User(getData().getPlanName(), CourseData.getInstance().getPassedCourseIds());
        try {
            FileUtils.writeJsonFile(userDataPath, user);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }
}
