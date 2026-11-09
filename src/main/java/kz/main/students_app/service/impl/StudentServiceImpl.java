package kz.main.students_app.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import kz.main.students_app.dto.StudentDto;
import kz.main.students_app.mapper.StudentMapper;
import kz.main.students_app.model.Student;
import kz.main.students_app.repository.StudentRepository;
import kz.main.students_app.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<StudentDto> getAllStudents() {
        return studentMapper.toDtoList(studentRepository.findAll(Sort.by(Sort.Direction.ASC, "id" )));
    }

    @Override
    public List<StudentDto> findStudentsBySearch(String word) {
        return studentMapper.toDtoList(studentRepository.findStudentsByWord(word));
    }

    @Override
    public StudentDto getStudent(int id) {
        return studentMapper.toDto(studentRepository.findById(id).get());
    }

    @Override
    public void updateStudent(StudentDto studentDto) {
        studentRepository.save(studentMapper.toModel(studentDto));
    }

    @Override
    public void addStudent(StudentDto studentDto) {
        studentRepository.save(studentMapper.toModel(studentDto));
    }

    @Override
    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

    public List<Student> findStudentsByFullNameAndGpa(String fullName, Double gpa){

        log.info("Start criteria builder: fullName: {}, gpa: {}", fullName, gpa);

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

    public List<StudentDto> findStudentsByGpaGreaterThan(Double gpa){

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> query  = cb.createQuery(Student.class);
        Root<Student> root = query.from(Student.class);

        Predicate predicate = cb.greaterThan(root.get("gpa"), gpa);

        query.select(root).where(predicate);

        return studentMapper.toDtoList(entityManager.createQuery(query).getResultList());

    }

    @Override
    public Page<Student> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public Page<Student> findByGpaGreaterThan(double gpa, Pageable pageable) {
        return studentRepository.findByGpaGreaterThan(gpa, pageable);
    }

    public StudentDto toDto(Student student){

        StudentDto studentDto = new StudentDto();

        studentDto.setId(student.getId());
        studentDto.setStudentProfileId(student.getStudentProfile());
        studentDto.setIin(student.getIin());
        studentDto.setGpa(student.getGpa());
        studentDto.setCity(student.getCity());
        studentDto.setSubjects(student.getSubjects());
        studentDto.setImyaFamiliya(student.getFullName());

        return studentDto;
    }

    public List<StudentDto> toDtoList(List<Student> students){
        List<StudentDto> studentDtos = new ArrayList<>();

        for(Student st: students){
            studentDtos.add(toDto(st));
        }

        return studentDtos;
    }
}
