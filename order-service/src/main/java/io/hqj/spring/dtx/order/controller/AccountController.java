package io.hqj.spring.dtx.order.controller;

import io.hqj.spring.dtx.order.domain.account.Account;
import io.hqj.spring.dtx.order.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;


    @PostMapping("/accounts")
    public Account createAccount(@RequestBody Account Account) {
        return accountService.createAccount(Account);
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Long id) {
        return accountService.findAccountById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
