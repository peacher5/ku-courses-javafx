package kucourses.services;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import kucourses.models.Plan;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.TreeMap;

public class PlanData {
    private static final String dir = "plans";

    public static TreeMap<String, Plan> getAllPlans() {
        TreeMap<String, Plan> plans = new TreeMap<>(Collections.reverseOrder());
        try {
            File planDir = new File(FileUtils.getJarDirPath() + "/" + dir);
            File[] files = planDir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        String fileName = file.getName();
                        if (fileName.endsWith(".json")) {
                            String planName = fileName.substring(0, fileName.lastIndexOf(".json"));
                            Plan plan = getPlan(planName);
                            if (plan != null)
                                plans.put(planName, plan);
                        }
                    }
                }
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return plans;
    }

    static Plan getPlan(String name) {
        String planJson;
        try {
            planJson = FileUtils.readFile(dir + "/" + name + ".json");
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Plan '" + name + ".json' not found");
        }
        try {
            return new Gson().fromJson(planJson, Plan.class);
        } catch (JsonSyntaxException e) {
            return null;
        }
    }
}
