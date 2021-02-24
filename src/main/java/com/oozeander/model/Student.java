package com.oozeander.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

@SuppressWarnings("serial")
@Entity
@Table(name = "student", schema = "public")
public class Student implements Serializable {
    @Id
    @SequenceGenerator(name = "student_seq_generator", schema = "default_schema", allocationSize = 1, initialValue = 1, sequenceName = "student_seq_generator")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq_generator")
    private Long id;
    @NotEmpty(message = "{student.name.notempty}")
    private String name;
    @NotEmpty(message = "{student.email.notempty}")
    @Email(message = "{student.email.notvalid}")
    private String email;
    @Transient
    private Integer age;
    @Column(name = "birth_date")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    public Student() {
    }

    public Student(String name, String email, Integer age, LocalDate birthDate) {
        super();
        this.name = name;
        this.email = email;
        this.age = age;
        this.birthDate = birthDate;
    }

    public Student(Long id, String name, String email, Integer age, LocalDate birthDate) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return Period.between(this.getBirthDate(), LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", email=" + email + ", age=" + age + ", birthDate=" + birthDate
                + "]";
    }
}