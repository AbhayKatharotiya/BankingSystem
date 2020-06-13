package bankingsystem.backend.dao;

import bankingsystem.backend.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findByContactNo(String contactNo);

}
