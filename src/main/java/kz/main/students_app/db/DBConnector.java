package kz.main.students_app.db;

import com.sun.jdi.connect.Connector;
import kz.main.students_app.model.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBConnector {

    private static Connection connection;
    private static String log = "postgres";
    private static String pas = "postgres";

    private static String url = "jdbc:postgresql://localhost:5432/G150";

    static {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, log, pas);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Student> getAllStudents(){

        ArrayList<Student> students = new ArrayList<>();

        try {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM students ORDER BY id ASC");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setGpa(resultSet.getDouble("gpa"));
                student.setCity(resultSet.getString("city"));
                student.setIin(resultSet.getString("iin"));
                student.setFullName(resultSet.getString("full_name"));

                students.add(student);
            }

            resultSet.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return students;
    }

    public static Student getStudentByID(int id){

        Student student = new Student();

        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM students WHERE id=?");

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                student.setId(resultSet.getInt("id"));
                student.setGpa(resultSet.getDouble("gpa"));
                student.setCity(resultSet.getString("city"));
                student.setIin(resultSet.getString("iin"));
                student.setFullName(resultSet.getString("full_name"));
            }

            resultSet.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return student;
    }

    public static void addStudent(Student student){

        try {

            PreparedStatement statement = connection.prepareStatement("INSERT INTO students (full_name, " +
                    "gpa, city, iin) VALUES (?, ?, ?, ?)");

            statement.setString(1, student.getFullName());
            statement.setDouble(2, student.getGpa());
            statement.setString(3, student.getCity());
            statement.setString(4, student.getIin());

            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void updateStudent(Student student){

        try {

            PreparedStatement statement = connection.prepareStatement("UPDATE students SET full_name=?, gpa=?, " +
                    "city=?, iin=? WHERE id=?");
            statement.setString(1, student.getFullName());
            statement.setDouble(2, student.getGpa());
            statement.setString(3, student.getCity());
            statement.setString(4, student.getIin());
            statement.setInt(5, student.getId());

            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void deleteStudentByID(int id){

        try {

            PreparedStatement statement = connection.prepareStatement("DELETE FROM students WHERE id=?");

            statement.setInt(1, id);

            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
