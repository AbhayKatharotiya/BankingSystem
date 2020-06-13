package bankingsystem.backend.dao;

import bankingsystem.backend.entity.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<Account,String> {

    Account findByAccountNo(String contactNo);

}
