package com.example.demo.entity;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** quizテーブル用：Entity */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Money {
    /** 識別ID */
    @Id
    private Integer id;

    private Integer lifeMoney;
    private Integer otherMoney;
    private Integer totalMoney;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLifeMoney() {
        return lifeMoney;
    }

    public void setLifeMoney(Integer lifeMoney) {
        this.lifeMoney = lifeMoney;
    }

    public Integer getOtherMoney() {
        return otherMoney;
    }

    public void setOtherMoney(Integer otherMoney) {
        this.otherMoney = otherMoney;
    }

    public Integer getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Integer totalMoney) {
        this.totalMoney = totalMoney;
    }
}
