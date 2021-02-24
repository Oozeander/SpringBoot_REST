package com.oozeander.service;

import com.oozeander.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> getStudents(String birthDate, String email);

    Student addStudent(Student student);

    Student getStudent(Long id);

    void deleteStudent(Long id);

    Student updateStudent(Long id, Student student);
}