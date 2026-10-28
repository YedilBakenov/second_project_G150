package kz.main.students_app.repository;

import kz.main.students_app.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("SELECT st FROM Student st WHERE st.fullName ilike concat('%', :word, '%') " +
            "OR st.city.cityName ilike concat('%', :word, '%')")
    List<Student> findStudentsByWord(String word);

}
