package org.example.services;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import java.math.BigDecimal;
import java.util.Optional;
import org.example.exceptions.AccountNotFoundException;
import org.example.models.Account;
import org.example.repositories.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@SpringBootTest
class AccountServiceIntegrationTests {
    @MockBean
    private AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;
    @Test
    @DisplayName("Test account service when transfer amount is less than the amount in the account")
    void testTransferAmount() {
        Account srcAcct = new Account();
        srcAcct.setId(1);
        srcAcct.setAmount(new BigDecimal(1000));
        Account dstAcct = new Account();
        dstAcct.setId(2);
        dstAcct.setAmount(new BigDecimal(1000));
        given(accountRepository.findById(srcAcct.getId())).willReturn(Optional.of(srcAcct));
        given(accountRepository.findById(dstAcct.getId())).willReturn(Optional.of(dstAcct));
        accountService.transferAmount(srcAcct.getId(), dstAcct.getId(), new BigDecimal(500));
        verify(accountRepository).changeAmount(srcAcct.getId(), new BigDecimal(500));
        verify(accountRepository).changeAmount(dstAcct.getId(), new BigDecimal(1500));
    }

    @Test
    void testTransferAmountFailsWhenSenderAccountNotFound() {
        Account dstAcct = new Account();
        dstAcct.setId(2);
        given(accountRepository.findById(1)).willReturn(Optional.empty());
        assertThrows(AccountNotFoundException.class, () -> accountService.transferAmount(1, dstAcct.getId(),
                new BigDecimal(100)));
        verify(accountRepository, never()).changeAmount(anyInt(), any());
    }
}
