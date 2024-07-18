package io.hqj.spring.dtx.order.controller;

import io.hqj.spring.dtx.common.domain.BusinessResponse;
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
    public ResponseEntity<BusinessResponse<Account>> createAccount(@RequestBody Account account) {
        try {
            return ResponseEntity.ok(BusinessResponse.getResponse(accountService.createAccount(account)));
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(BusinessResponse.getResponse(500, e.getMessage(), account));
        }
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<BusinessResponse<Account>> getAccount(@PathVariable Long id) {
        return accountService.findAccountById(id).map(product -> ResponseEntity.ok(BusinessResponse.getResponse(product)) ).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
