package com.oozeander;

import com.oozeander.model.Student;
import com.oozeander.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@SpringBootApplication
public class SpringBootAmigoscodeApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootAmigoscodeApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(final StudentRepository studentRepository) {
        return args -> {
            studentRepository.saveAll(List.of(
                    new Student("Billel KETROUCI", "billel.ketrouci@gmail.com", 24,
                            LocalDate.of(1996, Month.SEPTEMBER, 9)),
                    new Student("El Bakay BOURAJOINI", "elbakay@gmail.com", 27, LocalDate.of(1993, Month.APRIL, 6))));
        };
    }
}