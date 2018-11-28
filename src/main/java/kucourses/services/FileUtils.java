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
    static String readFileInside(String fileName) throws IOException {
        byte[] encoded = FileUtils.class.getResourceAsStream(fileName).readAllBytes();
        return new String(encoded, StandardCharsets.UTF_8);
    }

    static String readFileOutside(String fileName) throws IOException, URISyntaxException {
        return new String(Files.readAllBytes(Paths.get(getJarDirPath() + "/" + fileName)));
    }

    static void writeJsonFile(String fileName, Object object) throws IOException, URISyntaxException {
        try (Writer writer = new FileWriter(getJarDirPath() + "/" + fileName)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(object, writer);
        }
    }

    private static String getJarDirPath() throws URISyntaxException {
        return new File(FileUtils.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent();
    }
}
