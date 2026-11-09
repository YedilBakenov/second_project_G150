package kz.main.students_app.service;

import kz.main.students_app.dto.StudentDto;
import kz.main.students_app.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

    List<StudentDto> getAllStudents();

    List<StudentDto> findStudentsBySearch(String word);

    StudentDto getStudent(int id);

    void updateStudent(StudentDto student);

    void addStudent(StudentDto studentDto);

    void deleteStudent(int id);

    List<Student> findStudentsByFullNameAndGpa(String fullName, Double gpa);

    List<StudentDto> findStudentsByGpaGreaterThan(Double gpa);

    Page<Student> findAll(Pageable pageable);

    Page<Student> findByGpaGreaterThan(double gpa, Pageable pageable);

}
