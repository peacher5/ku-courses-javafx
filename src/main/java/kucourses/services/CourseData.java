package kucourses.services;

import com.google.gson.Gson;
import kucourses.models.Course;
import kucourses.models.CourseGroup;
import kucourses.models.PassedCourses;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class CourseData {
    private static CourseData courseData;
    private static final String userDataPath = "user.json";

    private CourseGroup[] courseGroups;
    private DataInfo dataInfo;

    public enum DataInfo {
        YEAR_61("courses_61", "แผนการศึกษาปี 2561"),
        YEAR_60("courses_60", "แผนการศึกษาปี 2560"),
        YEAR_55_BIO("courses_55_bio", "แผนการศึกษาปี 2555 (ชีววิทยา - โครงงาน)"),
        YEAR_55_CHEM("courses_55_chem", "แผนการศึกษาปี 2555 (เคมี - โครงงาน)");

        private String dataName, title;

        DataInfo(String dataName, String title) {
            this.dataName = dataName;
            this.title = title;
        }

        public String getPath() {
            return "/data/" + dataName + ".json";
        }

        public String getName() {
            return dataName;
        }

        public String getTitle() {
            return title;
        }

        public static DataInfo toDataInfo(String dataName) {
            for (DataInfo dataInfo : DataInfo.values())
                if (dataInfo.getName().equals(dataName))
                    return dataInfo;
            return null;
        }
    }

    private CourseData(DataInfo dataInfo) {
        this.dataInfo = dataInfo;

        String courseDataJson, userDataJson;
        try {
            courseDataJson = FileUtils.readFileInside(dataInfo.getPath());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Gson gson = new Gson();
        courseGroups = gson.fromJson(courseDataJson, CourseGroup[].class);

        // Init passed courses
        try {
            userDataJson = FileUtils.readFileOutside(userDataPath);
        } catch (IOException | URISyntaxException e) {
            return;
        }

        PassedCourses passedCourses = gson.fromJson(userDataJson, PassedCourses.class);

        if (dataInfo.getName().equals(passedCourses.getDataName()))
            for (String courseId : passedCourses.getPassedCoursesId())
                getById(courseId).setPassed(true);
    }

    public static CourseData getInstance() {
        if (courseData != null)
            return courseData;
        throw new IllegalStateException("DataInfo must be provided for 1st time used");
    }

    public static CourseData getInstance(DataInfo dataInfo) {
        if (courseData != null)
            return courseData;
        courseData = new CourseData(dataInfo);
        return courseData;
    }

    public static boolean isUserDataExists() {
        try {
            return FileUtils.isFileExists(userDataPath);
        } catch (URISyntaxException e) {
            return false;
        }
    }

    public static String getDataNameFromUserData() {
        String userDataJson = null;
        try {
            userDataJson = FileUtils.readFileOutside(userDataPath);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        PassedCourses passedCourses = new Gson().fromJson(userDataJson, PassedCourses.class);
        return passedCourses.getDataName();
    }

    public Course getById(String id) {
        for (CourseGroup courseGroup : courseGroups)
            for (Course course : courseGroup.getCourses())
                if (course.getId().equals(id))
                    return course;
        throw new IllegalArgumentException(String.format("Course not found (id=%s)", id));
    }

    public CourseGroup[] getAll() {
        return courseGroups;
    }

    public Course[] getAll(int year, int semester) {
        for (CourseGroup courseGroup : courseGroups)
            if (courseGroup.getYear() == year && courseGroup.getSemester() == semester)
                return courseGroup.getCourses();
        throw new IllegalArgumentException(String.format("Courses not found (year=%d, semester=%d)", year, semester));
    }

    public DataInfo getDataInfo() {
        return dataInfo;
    }

    public void savePassedCourses() {
        ArrayList<String> passedCourseIds = new ArrayList<>();
        for (CourseGroup courseGroup : courseGroups)
            for (Course course : courseGroup.getCourses())
                if (course.isPassed())
                    passedCourseIds.add(course.getId());

        PassedCourses passedCourses = new PassedCourses(dataInfo.getName(), passedCourseIds.toArray(new String[0]));
        try {
            FileUtils.writeJsonFile(userDataPath, passedCourses);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }
}
