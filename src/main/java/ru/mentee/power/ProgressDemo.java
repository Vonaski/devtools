package ru.mentee.power;

public class ProgressDemo {
    static void main() {
        var progress = new MenteeProgress("Ilyas Iksanov", 2, 20);

        System.out.println(progress.summary());
        if (progress.readyForSprint()) {
            System.out.println("Status: sprint ready");
        } else {
            System.out.println("Status: backlog first");
        }
    }
}
