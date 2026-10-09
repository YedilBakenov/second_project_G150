package kz.main.students_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class StudentsAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentsAppApplication.class, args);
    }

}
