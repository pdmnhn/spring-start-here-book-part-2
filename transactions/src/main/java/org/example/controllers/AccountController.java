package org.example.controllers;

import java.util.List;
import org.example.dto.TransferRequest;
import org.example.models.Account;
import org.example.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts")
    public List<Account> getAllAccounts() {
        return this.accountService.getAllAccounts();
    }

    @PostMapping("/transfers")
    public void transferMoney(@RequestBody TransferRequest transferRequest) {
        accountService.transferAmount(transferRequest.getSrcActId(), transferRequest.getDstActId(),
                transferRequest.getAmount());
    }
}
