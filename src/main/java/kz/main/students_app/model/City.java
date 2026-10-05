package kz.main.students_app.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class City {

    private Integer id;

    private String cityName;

    private String code;

    private double rating;

    private int countPeople;
}
