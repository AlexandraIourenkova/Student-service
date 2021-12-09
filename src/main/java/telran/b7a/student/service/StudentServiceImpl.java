package telran.b7a.student.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import telran.b7a.student.dao.StudentRepository;
import telran.b7a.student.dto.ScoreDto;
import telran.b7a.student.dto.StudentCredentialsDto;
import telran.b7a.student.dto.StudentDto;
import telran.b7a.student.dto.UpdateStudentDto;
import telran.b7a.student.model.Student;
@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	StudentRepository StudentRepository;

	@Override
	public boolean addStudent(StudentCredentialsDto studentCredentialsDto) {
		if (StudentRepository.findById(studentCredentialsDto.getId()) != null) {
			return false;
		}
		Student student = new Student(studentCredentialsDto.getId(), studentCredentialsDto.getName(),
				studentCredentialsDto.getPassword());
		StudentRepository.save(student);
		return true;
	}

	@Override
	public StudentDto findStudent(Integer id) {
		Student student = StudentRepository.findById(id);
		if(student == null) {
			return null;
		}
		return StudentDto.builder()
				.id(student.getId())
				.name(student.getName())
				.scores(student.getScores())
				.build();
	}

	@Override
	public StudentDto deleteStudent(Integer id) {
		StudentDto studentToRemove = findStudent(id);
		StudentRepository.deleteById(id);
		return studentToRemove;
	}

	@Override
	public StudentCredentialsDto updateStudent(Integer id, UpdateStudentDto updateStudentDto) {
		Student studentToUpdate = StudentRepository.findById(id);
		studentToUpdate.setName(updateStudentDto.getName());
		studentToUpdate.setPassword(updateStudentDto.getPassword());
		return StudentCredentialsDto.builder()
				.id(studentToUpdate.getId())
				.name(studentToUpdate.getName())
				.password(studentToUpdate.getPassword())
				.build();
	}

	@Override
	public boolean addScore(Integer id, ScoreDto scoreDto) {
		Student student = StudentRepository.findById(id);
		if(student == null) {
			return false;
		}
		return student.addScore(scoreDto.getExamName(), scoreDto.getScore());
	}

}
