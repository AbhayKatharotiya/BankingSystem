package bankingsystem.backend.controller;

import bankingsystem.backend.dto.Constants;
import bankingsystem.backend.dto.Response;
import bankingsystem.backend.dto.TransferMoneyDto;
import bankingsystem.backend.entity.Account;
import bankingsystem.backend.entity.Transaction;
import bankingsystem.backend.exception.BadRequestException;
import bankingsystem.backend.service.AccountService;
import bankingsystem.backend.service.TransactionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    private final Logger logger = LogManager.getLogger(getClass());

    @GetMapping("/account")
    public ResponseEntity<Account> getAccountDetails(HttpServletRequest request, HttpServletResponse response) {
        try {
            return ResponseEntity.ok(accountService.getAccountFromToken(request.getHeader("token")));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/transfer")
    public ResponseEntity<Response> transferMoney(@RequestBody TransferMoneyDto transferMoneyDto, HttpServletRequest request) {
        try {
            return ResponseEntity.ok(new Response(Constants.SUCCESS, transactionService.transferMoney(transferMoneyDto, request.getHeader("token"))));
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(Constants.ERROR, e.getMessage()));
        } catch (Exception e) {
            logger.error("error produced during transfer money : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(Constants.ERROR, e.getMessage()));
        }
    }

    @GetMapping("/transaction")
    public ResponseEntity<List<Transaction>> getTransactionHistory(HttpServletRequest request, HttpServletResponse response) {
        try {
            return ResponseEntity.ok(transactionService.getTransactionHostory(request.getHeader("token")));
        } catch (Exception e) {
            logger.error("error produced getting transaction history : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
        }
    }

}
