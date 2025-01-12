package com.example.demo.controller;

import com.example.demo.entity.Money;
import com.example.demo.repository.MoneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/** Quizコントローラー */
@Controller
@RequestMapping("/money-backward")
public class MoneyController {
    /**
     * DI対象
     */
    @Autowired
    MoneyRepository repository;

    /** 初期画面を表示します */
    @GetMapping
    public String showList(Money Money, Model model) {
        List<Money> moneyList = repository.findAll();
        int totalLifeMoney = 0;
        int totalOtherMoney = 0;

        // Calculate total lifeMoney and total otherMoney
        for (Money m : moneyList) {
            totalLifeMoney += m.getLifeMoney();
            totalOtherMoney += m.getOtherMoney();
            m.setTotalMoney(m.getLifeMoney() + m.getOtherMoney());
        }

        // Calculate percentages
        int totalMoney = totalLifeMoney + totalOtherMoney;
        double lifeMoneyPercentage = (totalMoney > 0) ? (totalLifeMoney * 100.0 / totalMoney) : 0.0;
        double otherMoneyPercentage = (totalMoney > 0) ? (totalOtherMoney * 100.0 / totalMoney) : 0.0;

        // Add the data to the model
        model.addAttribute("moneyList", moneyList);
        model.addAttribute("lifeMoneyPercentage", lifeMoneyPercentage);
        model.addAttribute("otherMoneyPercentage", otherMoneyPercentage);

        return "before";
    }

    /** 登録画面を表示します */
    @GetMapping("/registerForm")
    public String showRegister(Money Money, Model model) {
        model.addAttribute("Money", new Money());
        return "register";
    }

    /** 登録します */
    @PostMapping("/register")
    public String showAfter(Money Money, Model model) {
        repository.insert(1, Money.getLifeMoney(), Money.getOtherMoney());
        return "register-complete";
    }


}