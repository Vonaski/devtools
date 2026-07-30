package ru.mentee.power;

import ru.mentee.power.devtools.student.Student;
import ru.mentee.power.devtools.student.StudentList;

public class ProgressDemo {
    static void main() {
        Student student = new Student("Ilyas", "Tashkent");
        StudentList studentList = new StudentList();
        studentList.addStudent(student);
    }
}
