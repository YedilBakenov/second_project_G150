package kz.main.students_app.controller;

import kz.main.students_app.model.Student;
import kz.main.students_app.model.Subject;
import kz.main.students_app.service.CityService;
import kz.main.students_app.service.StudentService;
import kz.main.students_app.service.SubjectService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final SubjectService subjectService;
    private final CityService cityService;

    @GetMapping(value = "/") // http://localhost:8080/
    public String getStudents(Model model){
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("cities", cityService.getAllCities());
        return "index";
    }

    @GetMapping(value = "/search")
    public String getStudentsByWord(@RequestParam String word,
                                    Model model){
        model.addAttribute("students", studentService.findStudentsBySearch(word));
        model.addAttribute("cities", cityService.getAllCities());

        return "index";
    }

//    @GetMapping(value = "/details/{id}")
//    public String detailsPage(@PathVariable Integer id,
//                              Model model){
//
////        model.addAttribute("student", DBConnector.getStudentByID(id));
//
//        Student student = studentService.getStudent(id);
//
//        List<Subject> subjects = subjectService.getAllSubjects();
//
//        subjects.removeAll(student.getSubjects());
//
//        model.addAttribute("cities", cityService.getAllCities());
//
//        model.addAttribute("student", studentService.getStudent(id));
//
//        model.addAttribute("subjects", subjects);
//
//        return "details";
//    }

//    @PostMapping(value = "/update-student")
//    public String updateStudent(Student student){
////        DBConnector.updateStudent(student);
//
//        studentService.updateStudent(student);
//
//        return "redirect:/";
//    }

    @PostMapping(value = "/delete")
    public String deleteStudent(Integer id){
//        DBConnector.deleteStudentByID(id);
       studentService.deleteStudent(id);

        return "redirect:/";
    }

    @GetMapping(value = "/add-student")
    public String addStudent(Model model){

//        model.addAttribute("cities", DBConnector.getAllCities());

        return "add-page";
    }

//    @PostMapping(value = "/add-student")
//    public String addStudentToBase(Student st){
////        DBConnector.addStudent(st);
//        studentService.addStudent(stu);
//
//        return "redirect:/";
//    }

//    @PostMapping(value = "/delete-sub")
//    public String deleteSubject(@RequestParam int subject_id,
//                                @RequestParam int student_id){
//
//        Student student = studentService.getStudent(student_id);
//        Subject subject = subjectService.getSubject(subject_id);
//
//        student.getSubjects().remove(subject);
//
//        studentService.updateStudent(student);
//
//        return "redirect:/details/" + student_id;
//
//
//    }

//    @PostMapping(value = "/add-sub")
//    public String addSubject(@RequestParam int subject_id,
//                                @RequestParam int student_id){
//
//        Student student = studentService.getStudent(student_id);
//        Subject subject = subjectService.getSubject(subject_id);
//
//        student.getSubjects().add(subject);
//
//        studentService.updateStudent(student);
//
//        return "redirect:/details/" + student_id;
//
//    }

    @GetMapping(value = "/greater-gpa")
    public String getStudentsByGpaGreaterThen(Model model,
                                              @RequestParam double gpa){

        model.addAttribute("students", studentService.findStudentsByGpaGreaterThan(gpa));

        return "index";
    }

    @GetMapping(value = "/gpa-full-name")
    public String getStudentsByGpaAndFullName(Model model,
                                              @RequestParam(required = false) String fullName,
                                              @RequestParam(required = false) Double gpa){

        model.addAttribute("students", studentService.findStudentsByFullNameAndGpa(fullName, gpa));

        return "index";
    }

    @GetMapping(value = "/sort-pagination")
    public String getAllStudentsByPagination(Model model,
                                             @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "2") int size,
                                             @RequestParam(defaultValue = "id") String param,
                                             @RequestParam(defaultValue = "asc") String direction){

        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(param).descending(): Sort.by(param).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Student> studentPage = studentService.findAll(pageable);

        model.addAttribute("students", studentPage);

        return "index";

    }

    @GetMapping(value = "/gpa-pagination")
    public String getStudentsByGpaPagination(Model model,
                                             @RequestParam double gpa,
                                             @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "3") int size,
                                             @RequestParam(defaultValue = "id") String param,
                                             @RequestParam(defaultValue = "asc") String direction){

        Sort sort = direction.equalsIgnoreCase("desc")? Sort.by(param).descending():Sort.by(param).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Student> studentPage = studentService.findByGpaGreaterThan(gpa, pageable);

        model.addAttribute("students", studentPage);

        return "index";
    }

}
