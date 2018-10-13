package me.iampeach.kucourses.utils;

import java.util.Arrays;
import java.util.Collections;

public class Helper {
    public static boolean containString(String[] arr, String element) {
        return Arrays.stream(arr).anyMatch(element::equals);
    }

    public static boolean containAllString(String[] arr, String[] another) {
        int index = Collections.indexOfSubList(Arrays.asList(arr), Arrays.asList(another));

        return index >= 0 ? true : false;
    }
}
