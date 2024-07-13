package org.springdemo;

import org.springdemo.data.entities.Account;
import org.springdemo.data.entities.User;
import org.springdemo.data.repositories.UserRepository;
import org.springdemo.service.AccountService;
import org.springdemo.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Set;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final UserService userService;
    private final AccountService accountService;

    public CommandLineRunnerImpl(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) throws Exception {
//        User user=new User("Ali", 10);
//        this.userService.register(user);

//        User user = this.userService.findUserById(1);
//        User user2 = this.userService.findUserById(2);
//        Account account = new Account(BigDecimal.valueOf(55), user2);
//        this.accountService.createAccount(account);
//        Set<Account> accounts = user.getAccounts();
//        System.out.println(accounts.size());
//        Set<Account> accounts2 = user2.getAccounts();
//        System.out.println(accounts2.size());

//        this.accountService.withdrawMoney(BigDecimal.valueOf(50),1);
//        this.accountService.tranferMoney(BigDecimal.valueOf(1000),5);

        User lili = this.userService.findByName("Lili");
        System.out.println();
    }
}
