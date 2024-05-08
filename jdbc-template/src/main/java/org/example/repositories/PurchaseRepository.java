package org.example.repositories;

import java.util.List;
import org.example.models.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository
public class PurchaseRepository {
    private final JdbcTemplate jdbc;

    @Autowired
    public PurchaseRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public boolean createPurchaseOrder(Purchase purchase) {
        boolean successful;
        try {
            int count = jdbc.update("INSERT INTO purchase(product, price) values(?, ?)", purchase.getProduct(),
                    purchase.getPrice());
            assert count == 1;
            successful = true;
        } catch (DataAccessException e) {
            successful = false;
        }
        return successful;
    }

    public List<Purchase> getAllPurchases() {
        RowMapper<Purchase> rowMapper = (rs, unusedRowNum) -> new Purchase(rs.getInt("id"), rs.getString("product"),
                rs.getBigDecimal("price"));
        return jdbc.query("SELECT * FROM purchase", rowMapper);
    }
}
