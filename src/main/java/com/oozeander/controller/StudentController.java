package com.oozeander.controller;

import com.oozeander.model.ExceptionResponse;
import com.oozeander.model.Student;
import com.oozeander.service.StudentService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin
@Api(tags = {"Simple Student API Secured by Basic HTTP Auth"})
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "GET all students with filtering (if provided in request params)")
    @ApiResponse(code = 200, message = "Successfully retrieved list of students", response = Iterable.class)
    public List<Student> getStudents(
            @ApiParam(name = "birth_date", type = "String", value = "Filter by the year of birth date\nExample : /api/students?birth_date=1992 returns all students born in 1992 and above", required = false) @RequestParam(required = false, name = "birth_date", defaultValue = "") String birthDate,
            @ApiParam(name = "email", type = "String", required = false, value = "Filter by the prefix for email\nExample : /api/students?birth_date=1992&email=el returns (all students born in 1992 and above) AND (all students with emails that starts with 'el')") @RequestParam(required = false, name = "email", defaultValue = "") String email) {
        return studentService.getStudents(birthDate, email);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "POST a student given in the request body")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successfully saved one student", response = Student.class),
            @ApiResponse(code = 302, message = "ERROR, student already exists", response = ExceptionResponse.class),
            @ApiResponse(code = 400, message = "ERROR, invalid student passed in the request body", response = ExceptionResponse.class)
    })
    public Student registerStudent(@Valid @RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "GET a student by its ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieved one student by its ID", response = Student.class),
            @ApiResponse(code = 404, message = "ERROR, student not found", response = ExceptionResponse.class)
    })
    public Student getStudent(@PathVariable("id") Long id) {
        return studentService.getStudent(id);
    }

    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "MODIFY a student by its ID and a new student given in the request body")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully updated one student by its ID", response = Student.class),
            @ApiResponse(code = 404, message = "ERROR, student not found", response = ExceptionResponse.class),
            @ApiResponse(code = 400, message = "ERROR, invalid student passed in the request body", response = ExceptionResponse.class)
    })
    public Student updateStudent(@PathVariable("id") Long id, @Valid @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "DELETE a student by its id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully removed one student by its ID", response = Void.class),
            @ApiResponse(code = 404, message = "ERROR, student not found", response = ExceptionResponse.class)
    })
    public void deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
    }
}