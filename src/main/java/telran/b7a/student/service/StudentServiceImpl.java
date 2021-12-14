package telran.b7a.student.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.b7a.student.dao.StudentMongoRepository;
import telran.b7a.student.dto.ScoreDto;
import telran.b7a.student.dto.StudentCredentialsDto;
import telran.b7a.student.dto.StudentDto;
import telran.b7a.student.dto.UpdateStudentDto;
import telran.b7a.student.dto.exceptions.StudentNotFoundException;
import telran.b7a.student.model.Student;

@Service
public class StudentServiceImpl implements StudentService {
	
	StudentMongoRepository StudentRepository;
	ModelMapper modelMapper;
	
	
	@Autowired
	public StudentServiceImpl(StudentMongoRepository studentRepository, ModelMapper modelMapper) {
		StudentRepository = studentRepository;
		this.modelMapper = modelMapper;
		
	}

	@Override
	public boolean addStudent(StudentCredentialsDto studentCredentialsDto) {
		if (StudentRepository.findById(studentCredentialsDto.getId()).isPresent()) {
			return false;
		}
		Student student = modelMapper.map(studentCredentialsDto, Student.class);
		StudentRepository.save(student);
		return true;
	}

	@Override
	public StudentDto findStudent(Integer id) {
		Student student = StudentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
		return modelMapper.map(student, StudentDto.class);

	}

	@Override
	public StudentDto deleteStudent(Integer id) {
		StudentDto studentToRemove = findStudent(id);
		StudentRepository.deleteById(id);
		return studentToRemove;
	}

	@Override
	public StudentCredentialsDto updateStudent(Integer id, UpdateStudentDto updateStudentDto) {
		Student studentToUpdate = StudentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));

		studentToUpdate.setName(updateStudentDto.getName());
		studentToUpdate.setPassword(updateStudentDto.getPassword());
		return modelMapper.map(studentToUpdate, StudentCredentialsDto.class);
	}

	@Override
	public boolean addScore(Integer id, ScoreDto scoreDto) {
		Student student = StudentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
		boolean res = student.addScore(scoreDto.getExamName(), scoreDto.getScore());
		StudentRepository.save(student);
		return res;
	}

	@Override
	public List<StudentDto> findStudentsByName(String name) {
			return StudentRepository.findByNameIgnoreCase(name)
					.map(s -> modelMapper.map(s, StudentDto.class))
					.collect(Collectors.toList());
	}

	@Override
	public long getStudentsNameQuantity(List<String> names) {
		return StudentRepository.countByNameInIgnoreCase(names);
	}

	@Override
	public List<StudentDto> getStudentsByExamScore(String exam, int score) {
		return StudentRepository
				.findByExam(exam, score)
				.map(s -> modelMapper.map(s, StudentDto.class))
				.collect(Collectors.toList());
	}
	

}
