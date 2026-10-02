package kz.main.students_app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student {

    private Integer id;
    private static Integer idAdd = 4;
    private String fullName;

    private double gpa;

    private String city;

    private String iin;

    @Getter
    private static List<Student> students = new ArrayList<>();

    static {
        students.add(new Student(1, "Serik Serikov", 2.5, "Taraz", "9021312310"));
        students.add(new Student(2, "Berik Berikov", 3.5, "Shymkent", "9521312310"));
        students.add(new Student(3, "Merik Merikov", 4.5, "Almaty", "9721312310"));
    }


    public static Student getStudentById(int id) {
        return students.stream().filter(st -> st.getId()==id).findFirst().orElseThrow();
    }

    public static void addStudent(Student student) {
        student.setId(idAdd);
        idAdd++;
        students.add(student);
    }

    public static void deleteStudent(Integer id) {
        students.removeIf(st -> Objects.equals(st.getId(), id));
    }

    public static void updateStudent(Student student) {

        for (Student st : students) {
            if (Objects.equals(st.getId(), student.getId())) {
                st.setCity(student.getCity());
                st.setIin(student.getIin());
                st.setGpa(student.getGpa());
                st.setFullName(student.getFullName());
            }
        }
    }
}
