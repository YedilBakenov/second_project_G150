package kz.main.students_app.mapper;

import kz.main.students_app.dto.StudentDto;
import kz.main.students_app.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    @Mapping(source = "fullName", target = "imyaFamiliya")
    @Mapping(source = "studentProfile", target = "studentProfileId")
    StudentDto toDto(Student student);
    List<StudentDto> toDtoList(List<Student> students);

    @Mapping(source = "imyaFamiliya", target = "fullName")
    @Mapping(source = "studentProfileId", target = "studentProfile")
    Student toModel(StudentDto studentDto);

    List<Student> toModelList(List<StudentDto> studentDtos);
}
