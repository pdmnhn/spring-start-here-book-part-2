package org.example.controllers;

import java.util.List;
import org.example.dto.TransferRequest;
import org.example.models.Account;
import org.example.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts")
    public List<Account> getAllAccounts(@RequestParam(value = "name", required = false) String name) {
        return name == null ? accountService.getAllAccounts() : accountService.getAllAccountsByName(name);
    }

    @PostMapping("/transfers")
    public void transferMoney(@RequestBody TransferRequest transferRequest) {
        this.accountService.transferAmount(transferRequest.getSrcActId(), transferRequest.getDstActId(),
                transferRequest.getAmount());
    }
}
