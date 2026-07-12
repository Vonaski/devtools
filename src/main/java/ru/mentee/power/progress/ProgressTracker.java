package ru.mentee.power.progress;

public class ProgressTracker {
    public static String calculateTotalProgress(Mentee[] mentees) {
        int idx = 0;
        int totalTotal = 0;
        int totalCompleted = 0;
        while (idx < mentees.length) {
            totalTotal += mentees[idx].totalLessons();
            totalCompleted += mentees[idx].completedLessons();
            idx++;
        }
        int left = totalTotal - totalCompleted;
        String result = "Total: completed " + totalCompleted + " of " + totalTotal + " lessons, left " + left;
        System.out.println(result);
        return result;
    }
}
