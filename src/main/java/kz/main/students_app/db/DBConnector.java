package kz.main.students_app.db;

import kz.main.students_app.model.Student;
import kz.main.students_app.model.City;

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

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM students s INNER JOIN cities c " +
                    "ON s.city_id = c.id ORDER BY s.id ASC");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setGpa(resultSet.getDouble("gpa"));
                student.setIin(resultSet.getString("iin"));
                student.setFullName(resultSet.getString("full_name"));

//                City city = new City();
//                city.setId(resultSet.getInt("city_id"));
//                city.setCityName(resultSet.getString("city_name"));
//                city.setCode(resultSet.getString("code"));
//                city.setCountPeople(resultSet.getInt("count_people"));
//                city.setRating(resultSet.getDouble("rating"));
//
//                student.setCity(city);

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
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM students st " +
                    "INNER JOIN cities c ON st.city_id=c.id WHERE st.id=?");

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                student.setId(resultSet.getInt("id"));
                student.setGpa(resultSet.getDouble("gpa"));
                student.setIin(resultSet.getString("iin"));
                student.setFullName(resultSet.getString("full_name"));

//                City city = new City();
//                city.setId(resultSet.getInt("city_id"));
//                city.setCityName(resultSet.getString("city_name"));
//                city.setCode(resultSet.getString("code"));
//                city.setCountPeople(resultSet.getInt("count_people"));
//                city.setRating(resultSet.getDouble("rating"));
//
//                student.setCity(city);
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
                    "gpa, city_id, iin) VALUES (?, ?, ?, ?)");

            statement.setString(1, student.getFullName());
            statement.setDouble(2, student.getGpa());
//            statement.setInt(3, student.getCity().getId());
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
                    "city_id=?, iin=? WHERE id=?");
            statement.setString(1, student.getFullName());
            statement.setDouble(2, student.getGpa());
//            statement.setInt(3, student.getCity().getId());
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

    public static ArrayList<City> getAllCities(){
        ArrayList<City> newList = new ArrayList<>();

        try {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM cities");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                City city = new City();
                city.setId(resultSet.getInt("id"));
                city.setCityName(resultSet.getString("city_name"));
                city.setCode(resultSet.getString("code"));
                city.setCountPeople(resultSet.getInt("count_people"));
                city.setRating(resultSet.getDouble("rating"));

                newList.add(city);
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return newList;
        

    }


}
