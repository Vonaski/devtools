package ru.mentee.power;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class MenteeProgressTest {

  @Test
  void shouldReturnTrueWhenPlannedHoursAreAtLeastThree() {
    MenteeProgress progress = new MenteeProgress("Ilyas", 2, 5);
    boolean result = progress.readyForSprint();
    assertThat(result).isTrue();
  }

  @Test
  void shouldReturnFalseWhenPlannedHoursAreLessThanThree() {
    MenteeProgress progress = new MenteeProgress("Ilyas", 2, 2);
    boolean result = progress.readyForSprint();
    assertThat(result).isFalse();
  }

  @Test
  void shouldReturnFormattedSummary() {
    MenteeProgress progress = new MenteeProgress("Ilyas", 2, 20);
    String result = progress.summary();
    assertThat(result).isEqualTo("Sprint 2 → Ilyas: planned 20 h");
  }
}