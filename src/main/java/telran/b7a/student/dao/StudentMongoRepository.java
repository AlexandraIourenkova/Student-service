package telran.b7a.student.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.b7a.student.model.Student;

public interface StudentMongoRepository extends MongoRepository<Student, Integer> {

}
