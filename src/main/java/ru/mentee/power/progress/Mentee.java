package ru.mentee.power.progress;

public record Mentee(String name, String city,
                     String goal, int completedLessons, int totalLessons) {
    public Mentee {
        if (completedLessons < 0) {
            throw new IllegalArgumentException("Completed lessons must be greater than or equal to 0");
        }
        if (totalLessons <= 0) {
            throw new IllegalArgumentException("Total lessons must be greater than 0");
        }
        if (completedLessons > totalLessons) {
            throw new IllegalArgumentException("Completed lessons cannot be greater than total lessons");
        }
    }
}
