package kucourses.models;

public class CourseDifficulty {
    public enum Level {
        EASY("ง่าย"),
        MEDIUM("ปานกลาง"),
        HARD("ยาก");

        private String text;

        Level(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }

    public static Level getLevel(int difficulty) {
        if (difficulty < 30)
            return Level.EASY;
        else if (difficulty < 60)
            return Level.MEDIUM;
        return Level.HARD;
    }
}
