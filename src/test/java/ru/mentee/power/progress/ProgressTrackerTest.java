package ru.mentee.power.progress;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.*;

class ProgressTrackerTest {
    @Test
    @DisplayName("Должен корректно считать суммарный прогресс для нескольких менти")
    void shouldCalculateTotalProgress_whenMultipleMentees() {
        Mentee mentee1 = new Mentee("Ivan", "Moscow", "Offer", 5, 12);
        Mentee mentee2 = new Mentee("Mariya", "Moscow", "Offer", 8, 12);
        Mentee mentee3 = new Mentee("Petr", "Moscow", "Offer", 12, 12);
        Mentee[] mentees = {mentee1, mentee2, mentee3};

        String result = ProgressTracker.calculateTotalProgress(mentees);

        assertThat(result).contains("25 of 36");
    }

    @Test
    @DisplayName("Должен корректно считать прогресс, когда все менти завершили обучение")
    void shouldCalculateTotalProgress_whenAllMenteesCompleted() {
        Mentee mentee1 = new Mentee("Ivan", "Moscow", "Offer", 12, 12);
        Mentee mentee2 = new Mentee("Mariya", "Moscow", "Offer", 12, 12);
        Mentee[] mentees = {mentee1, mentee2};

        String result = ProgressTracker.calculateTotalProgress(mentees);

        assertThat(result).contains("24 of 24");
    }

    @Test
    @DisplayName("Должен выбрасывать IllegalArgumentException, если пройденных уроков больше, чем всего")
    void shouldThrowException_whenCompletedLessonsGreaterThanTotalLessons() {
        assertThatThrownBy(() -> new Mentee("Ivan", "Moscow", "Offer", 15, 12))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Completed lessons cannot be greater than total lessons");
    }

    @Test
    @DisplayName("Должен выбрасывать IllegalArgumentException при отрицательном количестве уроков")
    void shouldThrowException_whenLessonsAreNegative() {
        assertThatThrownBy(() -> new Mentee("Ivan", "Moscow", "Offer", -5, 12))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Completed lessons must be greater than or equal to 0");
    }

    @Test
    @DisplayName("Не должен выбрасывать исключений при передаче корректных данных")
    void shouldNotThrowAnyException_whenDataIsValid() {
        assertThatCode(() -> new Mentee("Ivan", "Moscow", "Offer", 5, 12))
                .doesNotThrowAnyException();
    }
}