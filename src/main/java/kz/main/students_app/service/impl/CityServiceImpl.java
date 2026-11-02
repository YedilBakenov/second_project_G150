package kz.main.students_app.service.impl;

import kz.main.students_app.model.City;
import kz.main.students_app.model.Student;
import kz.main.students_app.repository.CityRepository;
import kz.main.students_app.repository.StudentRepository;
import kz.main.students_app.service.CityService;
import kz.main.students_app.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }
}
