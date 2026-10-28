package kz.main.students_app.db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import kz.main.students_app.model.Student;
import kz.main.students_app.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CriteriaBuilderRequests {

    private final StudentRepository studentRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Student> findStudentsByFullNameAndGpa(String fullName, Double gpa){

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> query  = cb.createQuery(Student.class);
        Root<Student> root = query.from(Student.class);

        List<Predicate> predicates = new ArrayList<>();

        if( fullName!=null && !fullName.isEmpty()){
            predicates.add(cb.like(root.get("fullName"), fullName));
            query.select(root).where(predicates.toArray(new Predicate[0]));
        }

        if(gpa !=null){
            predicates.add(cb.greaterThanOrEqualTo(root.get("gpa"), gpa));
            query.select(root).where(predicates.toArray(new Predicate[0]));
        }

        return entityManager.createQuery(query).getResultList();

    }

    public List<Student> findStudentsByGpaGreaterThan(Double gpa){

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> query  = cb.createQuery(Student.class);
        Root<Student> root = query.from(Student.class);

        Predicate predicate = cb.greaterThan(root.get("gpa"), gpa);

        query.select(root).where(predicate);

        return entityManager.createQuery(query).getResultList();

    }


}
