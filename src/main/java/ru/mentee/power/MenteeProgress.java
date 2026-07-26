package ru.mentee.power;

public record MenteeProgress(String menteeName, int sprintNumber, int plannedHoursPerWeek) {
    public boolean ready_ForSprint () {
        return plannedHoursPerWeek >= 3;
    }

    public String summary() {
        return "Sprint %d → %s: planned %d h"
                .formatted(sprintNumber, menteeName, plannedHoursPerWeek);
    }
}
