package com.example.demo.repository;

import com.example.demo.entity.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class MoneyRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void insert(Integer id, Integer lifeMoney, Integer otherMoney) {
        String sql = "INSERT INTO money (life_money, other_money) VALUES (?, ?)";
        jdbcTemplate.update(sql, lifeMoney, otherMoney);
    }

    // findAllメソッド：moneyテーブルから全てのレコードを取得
    public List<Money> findAll() {
        String sql = "SELECT * FROM money";

        // RowMapperで結果をMoneyオブジェクトにマッピング
        return jdbcTemplate.query(sql, new RowMapper<Money>() {
            @Override
            public Money mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
                Money money = new Money();
                money.setLifeMoney(rs.getInt("life_money"));
                money.setOtherMoney(rs.getInt("other_money"));
                return money;
            }
        });
    }
}
