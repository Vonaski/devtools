package ru.mentee.power;

import ru.mentee.power.progress.Mentee;
import ru.mentee.power.progress.ProgressTracker;

public class ProgressDemo {
    static void main() {
        Mentee mentee1 = new Mentee("Ivan", "Moscow", "Offer", 5, 12);
        Mentee mentee2 = new Mentee("Mariya", "Moscow", "Offer", 8, 12);
        Mentee mentee3 = new Mentee("Petr", "Moscow", "Offer", 12, 12);

        Mentee[] mentees = new Mentee[]{mentee1, mentee2, mentee3};

        ProgressTracker.calculateTotalProgress(mentees);
    }
}
