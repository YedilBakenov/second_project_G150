package kz.main.students_app.controller;

import kz.main.students_app.db.DBConnector;
import kz.main.students_app.model.Student;
import kz.main.students_app.model.Subject;
import kz.main.students_app.repository.CityRepository;
import kz.main.students_app.repository.StudentProfileRepository;
import kz.main.students_app.repository.StudentRepository;
import kz.main.students_app.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;

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

    private final StudentRepository studentRepository;
    private final CityRepository cityRepository;
    private final StudentProfileRepository studentProfileRepository;
    private final SubjectRepository subjectRepository;

    @GetMapping(value = "/") // http://localhost:8080/
    public String getStudents(Model model){
        model.addAttribute("students", studentRepository.findAll(Sort.by(Sort.Direction.DESC, "id")));
        model.addAttribute("cities", cityRepository.findAll());
        return "index";
    }

    @GetMapping(value = "/details/{id}")
    public String detailsPage(@PathVariable Integer id,
                              Model model){

//        model.addAttribute("student", DBConnector.getStudentByID(id));

        Student student = studentRepository.findById(id).orElseThrow();

        List<Subject> subjects = subjectRepository.findAll();

         subjects.removeAll(student.getSubjects());

        model.addAttribute("cities", cityRepository.findAll());

        model.addAttribute("student", studentRepository.findById(id).orElseThrow());

        model.addAttribute("subjects", subjects);

        return "details";
    }

    @PostMapping(value = "/update-student")
    public String updateStudent(Student student){
//        DBConnector.updateStudent(student);

        studentRepository.save(student);

        return "redirect:/";
    }

    @PostMapping(value = "/delete")
    public String deleteStudent(Integer id){
//        DBConnector.deleteStudentByID(id);
        studentRepository.deleteById(id);

        return "redirect:/";
    }

    @GetMapping(value = "/add-student")
    public String addStudent(Model model){

//        model.addAttribute("cities", DBConnector.getAllCities());

        return "add-page";
    }

    @PostMapping(value = "/add-student")
    public String addStudentToBase(Student st){
//        DBConnector.addStudent(st);
        studentRepository.save(st);

        return "redirect:/";
    }

    @PostMapping(value = "/delete-sub")
    public String deleteSubject(@RequestParam int subject_id,
                                @RequestParam int student_id){

        Student student = studentRepository.findById(student_id).orElseThrow();
        Subject subject = subjectRepository.findById(subject_id).orElseThrow();

        student.getSubjects().remove(subject);

        studentRepository.save(student);

        return "redirect:/details/" + student_id;


    }

    @PostMapping(value = "/add-sub")
    public String addSubject(@RequestParam int subject_id,
                                @RequestParam int student_id){

        Student student = studentRepository.findById(student_id).orElseThrow();
        Subject subject = subjectRepository.findById(subject_id).orElseThrow();

        student.getSubjects().add(subject);

        studentRepository.save(student);

        return "redirect:/details/" + student_id;


    }
}
