package org.example.repositories;

import java.math.BigDecimal;
import java.util.List;
import org.example.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository
public class AccountRepository {
    private final JdbcTemplate jdbc;
    private final static RowMapper<Account> ACCOUNT_ROW_MAPPER = (row, rowIndex) -> {
        Account account = new Account();
        account.setId(row.getInt("id"));
        account.setName(row.getString("name"));
        account.setAmount(row.getBigDecimal("amount"));
        return account;
    };

    @Autowired
    public AccountRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Account findAccountById(int id) {
        String sql = "SELECT * FROM account WHERE id = ?";
        return jdbc.queryForObject(sql, ACCOUNT_ROW_MAPPER, id);
    }

    public void changeAmount(int id, BigDecimal amount) {
        String sql = "UPDATE account SET amount = ? WHERE ID = ?";
        jdbc.update(sql, amount, id);
    }

    public List<Account> findAllAccounts() {
        return jdbc.query("SELECT * FROM account", ACCOUNT_ROW_MAPPER);
    }
}
