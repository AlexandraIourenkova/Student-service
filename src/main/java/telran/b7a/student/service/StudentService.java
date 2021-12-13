package telran.b7a.student.service;

import java.util.List;

import telran.b7a.student.dto.ScoreDto;
import telran.b7a.student.dto.StudentCredentialsDto;
import telran.b7a.student.dto.StudentDto;
import telran.b7a.student.dto.UpdateStudentDto;

public interface StudentService {
	boolean addStudent(StudentCredentialsDto studentCredentialsDto);

	StudentDto findStudent(Integer id);

	StudentDto deleteStudent(Integer id);

	StudentCredentialsDto updateStudent(Integer id, UpdateStudentDto updateStudentDto);

	boolean addScore(Integer id, ScoreDto scoreDto);
	
	List<StudentDto> findStudentsByName(String name);
}
