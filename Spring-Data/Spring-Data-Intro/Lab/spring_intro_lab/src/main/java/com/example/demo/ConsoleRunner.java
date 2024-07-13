package com.example.demo;

import com.example.demo.models.Account;
import com.example.demo.models.User;
import com.example.demo.repositories.AccountRepository;
import com.example.demo.services.AccountService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final UserService userService;
    private final AccountService accountService;

    public ConsoleRunner(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User("Misho", 28);
        Account account = new Account(new BigDecimal("40000"), user);

        user.addAccount(account);
        userService.registerUser(user);

        accountService.depositMoney(new BigDecimal("400"), account.getId());
        accountService.withdrawMoney(new BigDecimal("5000"), account.getId());

    }
}
