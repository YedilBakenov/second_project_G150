package kz.main.students_app.service;

import kz.main.students_app.model.City;
import kz.main.students_app.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CityService {

    List<City> getAllCities();


}
