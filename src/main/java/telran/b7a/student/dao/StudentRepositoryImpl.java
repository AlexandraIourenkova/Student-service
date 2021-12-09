package telran.b7a.student.dao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import telran.b7a.student.model.Student;

@Repository
public class StudentRepositoryImpl implements StudentRepository {
	Map<Integer, Student> students = new ConcurrentHashMap<>();

	@Override
	public Student save(Student student) {
		students.put(student.getId(), student);
		return student;
	}

	@Override
	public Student findById(int id) {
		return students.get(id);
	}

	@Override
	public Student deleteById(int id) {
		return students.remove(id);
	}

}
