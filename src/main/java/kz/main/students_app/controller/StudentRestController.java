package kz.main.students_app.controller;

import kz.main.students_app.model.Student;
import kz.main.students_app.model.Subject;
import kz.main.students_app.service.StudentService;
import kz.main.students_app.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/student") //localhost:8080/student
@RequiredArgsConstructor
public class StudentRestController {

    private final StudentService studentService;
    private final SubjectService subjectService;

    @GetMapping(value = "/")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping(value = "/get-subjects")
    public List<Subject> getAllSubjects(){
        return subjectService.getAllSubjects();
    }

    @GetMapping(value = "/get-student/{id}")
    public Student getStudentById(@PathVariable int id){
        return studentService.getStudent(id);
    }

    @GetMapping(value = "/search")
    public List<Student> getStudentsByWord(@RequestParam String word){
        return studentService.findStudentsBySearch(word);
    }

    @PostMapping(value = "/add-student")
    public void addStudent(@RequestBody Student student){
         studentService.addStudent(student);
    }

    @PutMapping(value = "/update-student")
    public void updateStudent(@RequestBody Student student){
        studentService.updateStudent(student);
    }

    @DeleteMapping(value = "/delete")
    public void deleteStudent(@RequestParam int id){
        studentService.deleteStudent(id);
    }

    @PatchMapping(value = "/delete-sub")
    public void deleteSubject(@RequestParam int subject_id,
                                @RequestParam int student_id) {

        Student student = studentService.getStudent(student_id);
        Subject subject = subjectService.getSubject(subject_id);

        student.getSubjects().remove(subject);

        studentService.updateStudent(student);

    }

    @PatchMapping(value = "/add-sub")
    public void addSubject(@RequestParam int subject_id,
                             @RequestParam int student_id){

        Student student = studentService.getStudent(student_id);
        Subject subject = subjectService.getSubject(subject_id);

        student.getSubjects().add(subject);

        studentService.updateStudent(student);

    }

    @GetMapping(value = "/greater-gpa")
    public List<Student> getStudentsByGpaGreaterThen(
                                              @RequestParam double gpa){

       return studentService.findStudentsByGpaGreaterThan(gpa);
    }

    @GetMapping(value = "/gpa-full-name")
    public List<Student> getStudentsByGpaAndFullName(Model model,
                                              @RequestParam(required = false) String fullName,
                                              @RequestParam(required = false) Double gpa){

      return   studentService.findStudentsByFullNameAndGpa(fullName, gpa);

    }

    @GetMapping(value = "/sort-pagination")
    public List<Student> getAllStudentsByPagination(
                                             @RequestParam(defaultValue = "1") int page,
                                             @RequestParam(defaultValue = "3") int size,
                                             @RequestParam(defaultValue = "id") String param,
                                             @RequestParam(defaultValue = "asc") String direction){

        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(param).descending(): Sort.by(param).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Student> studentPage = studentService.findAll(pageable);

        return studentPage.stream().toList();

    }

    @GetMapping(value = "/gpa-pagination")
    public List<Student> getStudentsByGpaPagination(
                                             @RequestParam double gpa,
                                             @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "3") int size,
                                             @RequestParam(defaultValue = "id") String param,
                                             @RequestParam(defaultValue = "asc") String direction){

        Sort sort = direction.equalsIgnoreCase("desc")? Sort.by(param).descending():Sort.by(param).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Student> studentPage = studentService.findByGpaGreaterThan(gpa, pageable);

        return studentPage.toList();
    }

}
