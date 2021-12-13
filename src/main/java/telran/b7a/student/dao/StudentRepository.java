package telran.b7a.student.dao;

import java.util.List;
import java.util.Optional;

import telran.b7a.student.model.Student;

public interface StudentRepository {
	
	Student save(Student student);

	Optional<Student> findById(int id);

	Student deleteById(int id);
	
	List<Student> findAll();

}
