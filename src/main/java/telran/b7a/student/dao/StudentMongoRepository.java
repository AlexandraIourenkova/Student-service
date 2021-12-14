package telran.b7a.student.dao;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import telran.b7a.student.model.Student;

public interface StudentMongoRepository extends MongoRepository<Student, Integer> {
	Stream<Student> findBy();

	Stream<Student> findByNameIgnoreCase(String nameString);

	long countByNameInIgnoreCase(List<String> names);

	@Query("{'scores.?0': {$gte: ?1}}")
	Stream<Student> findByExam(String exam, int score);
}
