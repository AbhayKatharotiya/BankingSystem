package bankingsystem.backend.service;

import bankingsystem.backend.config.JwtTokenUtil;
import bankingsystem.backend.dao.TransactionRepository;
import bankingsystem.backend.dto.Constants;
import bankingsystem.backend.dto.TransferMoneyDto;
import bankingsystem.backend.entity.Account;
import bankingsystem.backend.entity.Transaction;
import bankingsystem.backend.exception.BadRequestException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private final Logger logger = LogManager.getLogger(getClass());

    public String transferMoney(TransferMoneyDto transferMoneyDto, String token) {
        String transferFrom = jwtTokenUtil.getUsernameFromToken(token);
        Account accountTo = accountService.getAccountByAccountNo(transferMoneyDto.getTransferTo());
        Account accountFrom = accountService.getAccountByAccountNo(transferFrom);

        if (accountTo == null) {
            logger.info("Account not found with account no : {}", transferMoneyDto.getTransferTo());
            throw new BadRequestException("Account not found");
        }
        if (accountTo.getAccountNo().equals(accountFrom.getAccountNo())) {
            throw new BadRequestException("Can not transfer yourself");
        }
        if (accountFrom.getBalance() < transferMoneyDto.getAmount()) {
            logger.error("Not enough balance in account : {}", transferFrom);
            throw new BadRequestException("Not enough balance");
        }

        return makeTransaction(accountFrom, accountTo, transferMoneyDto.getAmount());


    }

    private String makeTransaction(Account accountFrom, Account accountTo, Long amount) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTransferFrom(accountFrom.getAccountNo());
        transaction.setTransferTo(accountTo.getAccountNo());
        transaction.setDate(new Date());
        transactionRepository.save(transaction);
        accountFrom.setBalance(accountFrom.getBalance() - amount);
        accountService.updateAccount(accountFrom);
        accountTo.setBalance(accountTo.getBalance() + amount);
        accountService.updateAccount(accountTo);
        return Constants.TRANSACTION_DONE;

    }

    public List<Transaction> getTransactionHostory(String token) {
        String accountNo = jwtTokenUtil.getUsernameFromToken(token);
        List<Transaction> transactions = new ArrayList<>();
        transactions.addAll(transactionRepository.findByTransferFrom(accountNo));
        transactions.addAll(transactionRepository.findByTransferTo(accountNo));
        transactions.sort(Collections.reverseOrder());
        return transactions;
    }
}
