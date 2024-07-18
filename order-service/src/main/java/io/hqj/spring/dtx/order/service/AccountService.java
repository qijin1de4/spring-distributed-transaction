package io.hqj.spring.dtx.order.service;

import io.hqj.spring.dtx.order.domain.account.Account;
import io.hqj.spring.dtx.order.repository.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(Account account) {
        if(account.getBalance() < 0) {
            throw new RuntimeException("账户余额必须大于或等于0！");
        }
        return accountRepository.save(account);
    }

    public Optional<Account> findAccountById(Long id) {
        return accountRepository.findById(id);
    }

    public Account updateAccount(Account account) {
        if(account.getBalance() < 0) {
            throw new RuntimeException("账户余额必须大于或等于0！");
        }
        return accountRepository.save(account);
    }

}
