package kz.main.students_app.controller;

import kz.main.students_app.db.DBConnector;
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
        model.addAttribute("students", DBConnector.getAllStudents());
        model.addAttribute("cities", DBConnector.getAllCities());
        return "index";
    }

    @GetMapping(value = "/details/{id}")
    public String detailsPage(@PathVariable Integer id,
                              Model model){

        model.addAttribute("student", DBConnector.getStudentByID(id));
        model.addAttribute("cities", DBConnector.getAllCities());

        return "details";
    }

    @PostMapping(value = "/update-student")
    public String updateStudent(Student student){
        DBConnector.updateStudent(student);

        return "redirect:/";
    }

    @PostMapping(value = "/delete")
    public String deleteStudent(Integer id){
        DBConnector.deleteStudentByID(id);
        return "redirect:/";
    }

    @GetMapping(value = "/add-student")
    public String addStudent(Model model){

        model.addAttribute("cities", DBConnector.getAllCities());

        return "add-page";
    }

    @PostMapping(value = "/add-student")
    public String addStudentToBase(Student st){
        DBConnector.addStudent(st);

        return "redirect:/";
    }
}
