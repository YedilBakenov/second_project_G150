package kz.main.students_app.controller;

import kz.main.students_app.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {

    @GetMapping(value = "/") // http://localhost:8080/cars
    public String getStudents(Model model){
        model.addAttribute("students", Student.getStudents());
        return "index";
    }

    @GetMapping(value = "/details/{id}")
    public String detailsPage(@PathVariable Integer id,
                              Model model){

        System.out.println(id);
        model.addAttribute("student", Student.getStudentById(id));

        return "details";
    }

    @PostMapping(value = "/update-student")
    public String updateStudent(Student student){
        Student.updateStudent(student);

        return "redirect:/";
    }

    @PostMapping(value = "/delete")
    public String deleteStudent(Integer id){
        Student.deleteStudent(id);
        return "redirect:/";
    }

    @GetMapping(value = "/add-student")
    public String addStudent(){

        return "add-page";
    }

    @PostMapping(value = "/add-student")
    public String addStudentToBase(Student st){
        Student.addStudent(st);

        return "redirect:/";
    }
}
