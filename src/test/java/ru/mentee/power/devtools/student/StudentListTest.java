package ru.mentee.power.devtools.student;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

class StudentListTest {

    @Test
    void shouldAddStudent() {
        StudentList list = new StudentList();
        Student student = new Student("Ilyas", "Urgench");
        list.addStudent(student);
        List<Student> result = list.getStudentByCity("Urgench");
        assertThat(result).hasSize(1).contains(student);
    }

    @Test
    void shouldIgnoreNullStudent() {
        StudentList list = new StudentList();
        list.addStudent(null);
        List<Student> result = list.getStudentByCity("Urgench");
        assertThat(result).isEmpty();
    }

    @Test
    void shouldReturnStudentsFromRequestedCity() {
        StudentList list = new StudentList();

        Student first = new Student("Ilyas", "Urgench");
        Student second = new Student("Alex", "Tashkent");
        Student third = new Student("Kate", "Urgench");

        list.addStudent(first);
        list.addStudent(second);
        list.addStudent(third);

        List<Student> result = list.getStudentByCity("Urgench");
        assertThat(result).containsExactly(first, third);
    }

    @Test
    void shouldReturnEmptyListWhenCityNotFound() {
        StudentList list = new StudentList();
        list.addStudent(new Student("Ilyas", "Urgench"));
        List<Student> result = list.getStudentByCity("Samarkand");
        assertThat(result).isEmpty();
    }
}