package telran.b7a.student.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import telran.b7a.student.model.Student;

//@Repository
public class StudentRepositoryImpl implements StudentRepository {
	Map<Integer, Student> students = new ConcurrentHashMap<>();

	@Override
	public Student save(Student student) {
		students.put(student.getId(), student);
		return student;
	}

	@Override
	public Optional<Student> findById(int id) {
		return Optional.ofNullable(students.get(id));
	}

	@Override
	public Student deleteById(int id) {
		return students.remove(id);
	}

	@Override
	public List<Student> findAll() {
		return new ArrayList<>(students.values());
	}

}
