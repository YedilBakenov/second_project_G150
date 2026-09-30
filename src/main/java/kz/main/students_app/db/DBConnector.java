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

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM students ORDER BY id DESC");

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


}
