package org.example.services;

import java.math.BigDecimal;
import java.util.List;
import org.example.exceptions.AccountNotFoundException;
import org.example.models.Account;
import org.example.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void transferAmount(int srcActId, int dstActId, BigDecimal amount) {
        Account srcAct = accountRepository.findById(srcActId).orElseThrow(AccountNotFoundException::new);
        Account dstAct = accountRepository.findById(dstActId).orElseThrow(AccountNotFoundException::new);
        BigDecimal srcActNewBalance = srcAct.getAmount().subtract(amount);
        BigDecimal dstActNewBalance = dstAct.getAmount().add(amount);
        accountRepository.changeAmount(srcActId, srcActNewBalance);
        accountRepository.changeAmount(dstActId, dstActNewBalance);
    }

    public List<Account> getAllAccounts() {
        return (List<Account>) accountRepository.findAll();
    }

    public List<Account> getAllAccountsByName(String name) {
        return accountRepository.findAllAccountsByName(name);
    }
}
