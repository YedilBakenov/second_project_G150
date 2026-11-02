package kz.main.students_app.service;

import kz.main.students_app.model.Student;
import kz.main.students_app.model.Subject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubjectService {

    List<Subject> getAllSubjects();

    Subject getSubject(int id);

}
