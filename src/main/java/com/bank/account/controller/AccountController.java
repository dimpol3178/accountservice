package com.bank.account.controller;

import com.bank.account.dto.AccountRequest;
import com.bank.account.dto.CreateAccountResponse;
import com.bank.account.dto.CustomerInquiryResponse;
import com.bank.account.service.CustomerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    private final CustomerAccountService service;

    @Autowired
    public AccountController(CustomerAccountService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CreateAccountResponse> createAccount(@Valid @RequestBody AccountRequest request) {
        CreateAccountResponse response = service.createCustomerAccount(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{customerNumber}")
    public ResponseEntity<CustomerInquiryResponse> getAccount(@PathVariable Long customerNumber) {
        CustomerInquiryResponse response = service.getCustomerInquiry(customerNumber);
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }
}