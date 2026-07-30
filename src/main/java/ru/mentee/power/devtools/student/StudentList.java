package ru.mentee.power.devtools.student;

import java.util.ArrayList;
import java.util.List;

public class StudentList {
    private List<Student> studentList;

    public StudentList() {
        studentList = new ArrayList<>();
    }

    // TODO: Add duplicate student validation
    public void addStudent(Student student) {
        System.out.println(student + " added");
        if (student != null) {
            studentList.add(student);
        }
    }

    public List<Student> getStudentByCity(String city) {
        return studentList.stream().filter(s -> s.city().equals(city)).toList();
    }
}
