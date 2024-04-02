package org.springdemo.service.impl;

import org.springdemo.data.entities.Account;
import org.springdemo.data.repositories.AccountRepository;
import org.springdemo.service.AccountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void createAccount(Account account) {
        this.accountRepository.saveAndFlush(account);
    }

    @Override
    public void withdrawMoney(BigDecimal money, Integer id) {
        Optional<Account> optional = this.accountRepository.findById(id);
        if (optional.isPresent()) {
            Account account = optional.get();
            if (account.getBalance().compareTo(money) >= 0) {
                account.setBalance(account.getBalance().subtract(money));
                this.accountRepository.saveAndFlush(account);
            }
        }
    }

    @Override
    public void tranferMoney(BigDecimal money, Integer id) {
        Optional<Account> optional = this.accountRepository.findById(id);
        if (optional.isPresent()) {
            Account account = optional.get();
            if (money.doubleValue() >= 0) {
                account.setBalance(account.getBalance().add(money));
                this.accountRepository.saveAndFlush(account);
            }
        }
    }

    private final AccountRepository accountRepository;

}
