package kz.main.students_app.repository;

import kz.main.students_app.model.City;
import kz.main.students_app.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
}
