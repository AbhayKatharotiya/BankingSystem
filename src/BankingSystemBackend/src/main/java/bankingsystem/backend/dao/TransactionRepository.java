package bankingsystem.backend.dao;

import bankingsystem.backend.entity.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {

    List<Transaction> findByTransferFrom(String transferFrom);

    List<Transaction> findByTransferTo(String transferTo);

}
