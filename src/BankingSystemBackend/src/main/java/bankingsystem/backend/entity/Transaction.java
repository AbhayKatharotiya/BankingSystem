package bankingsystem.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Transaction implements Comparable<Transaction> {

    @Id
    private String id;
    private String transferFrom;
    private String transferTo;
    private Long amount;
    private Date date;


    @Override
    public int compareTo(Transaction transaction) {
        return this.getDate().compareTo(transaction.getDate());
    }
}
