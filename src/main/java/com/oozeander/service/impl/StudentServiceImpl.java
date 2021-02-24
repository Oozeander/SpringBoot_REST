package com.oozeander.service.impl;

import com.oozeander.exception.StudentAlreadyExistsException;
import com.oozeander.exception.StudentNotFoundException;
import com.oozeander.model.Student;
import com.oozeander.repository.StudentRepository;
import com.oozeander.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("studentService")
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getStudents(String birthDate, String email) {
        List<Student> students = studentRepository.findAll();
        if (!birthDate.isEmpty()) {
            students = students.stream().filter(student -> student.getBirthDate().getYear() > Integer.parseInt(birthDate)).collect(Collectors.toList());
        }
        if (!email.isEmpty()) {
            students = students.stream().filter(student -> student.getEmail().startsWith(email)).collect(Collectors.toList());
        }
        return students;
    }

    @Override
    public Student addStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent())
            throw new StudentAlreadyExistsException(studentOptional.get().getId());
        return studentRepository.save(student);
    }

    @Override
    public Student getStudent(Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent())
            return studentOptional.get();
        else throw new StudentNotFoundException(id);
    }

    @Override
    public void deleteStudent(Long id) {
        if (studentRepository.existsById(id))
            studentRepository.deleteById(id);
        else throw new StudentNotFoundException(id);
    }

    @Override
    @Transactional
    public Student updateStudent(Long id, Student student) {
        if (studentRepository.existsById(id)) {
            Student studentToUpdate = studentRepository.findById(id).get();
            studentToUpdate.setName(student.getName());
            studentToUpdate.setBirthDate(student.getBirthDate());
            studentToUpdate.setEmail(student.getEmail());
            return studentToUpdate;
        } else throw new StudentNotFoundException(id);
    }
}