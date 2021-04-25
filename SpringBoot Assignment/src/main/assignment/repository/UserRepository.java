package user.assignment.repository;


import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.CrudRepository;
import user.assignment.entity.User;


public interface UserRepository extends 
		JpaRepositoryImplementation<User, String> {
		
		


}
