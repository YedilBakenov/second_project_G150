package kz.main.students_app.service.impl;

import kz.main.students_app.model.City;
import kz.main.students_app.model.Subject;
import kz.main.students_app.repository.CityRepository;
import kz.main.students_app.repository.SubjectRepository;
import kz.main.students_app.service.CityService;
import kz.main.students_app.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject getSubject(int id) {
        return subjectRepository.findById(id).get();
    }
}
