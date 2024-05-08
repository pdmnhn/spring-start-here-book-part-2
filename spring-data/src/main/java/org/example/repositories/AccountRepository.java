package org.example.repositories;

import java.math.BigDecimal;
import java.util.List;
import org.example.models.Account;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;


public interface AccountRepository extends CrudRepository<Account, Integer> {
    List<Account> findAllAccountsByName(String name);

    @Modifying
    @Query("UPDATE account SET amount = :amount WHERE id = :id")
    void changeAmount(int id, BigDecimal amount);
}
