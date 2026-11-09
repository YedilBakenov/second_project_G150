package kz.main.students_app.dto;

import jakarta.persistence.*;
import kz.main.students_app.model.City;
import kz.main.students_app.model.StudentProfile;
import kz.main.students_app.model.Subject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentDto {

    private Integer id;

    private String imyaFamiliya;

    private double gpa;

    private String iin;

    private StudentProfile studentProfileId;

    private City city;

    private List<Subject> subjects;
}
