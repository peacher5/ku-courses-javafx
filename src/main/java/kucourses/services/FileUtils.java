package kucourses.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

class FileUtils {
    static String readFile(String fileName) throws IOException, URISyntaxException {
        return new String(Files.readAllBytes(Paths.get(getJarDirPath() + "/" + fileName)), StandardCharsets.UTF_8);
    }

    static void writeJsonFile(String fileName, Object object) throws IOException, URISyntaxException {
        try (Writer writer = new FileWriter(getJarDirPath() + "/" + fileName)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(object, writer);
        }
    }

    static boolean isFileExists(String fileName) throws URISyntaxException {
        File file = new File(getJarDirPath() + "/" + fileName);
        return file.exists() && !file.isDirectory();
    }

    static String getJarDirPath() throws URISyntaxException {
        return new File(FileUtils.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent();
    }
}
