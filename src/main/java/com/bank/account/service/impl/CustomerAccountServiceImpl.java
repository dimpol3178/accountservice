package com.bank.account.service.impl;

import com.bank.account.constant.AppConstants;
import com.bank.account.dto.*;
import com.bank.account.entity.Account;
import com.bank.account.entity.Customer;
import com.bank.account.exception.CustomerNotFoundException;
import com.bank.account.repositories.CustomerRepository;
import com.bank.account.service.CustomerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerAccountServiceImpl implements CustomerAccountService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerAccountServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public CreateAccountResponse createCustomerAccount(AccountRequest request) {
        Customer customer = Customer.builder()
                .customerName(request.getCustomerName())
                .customerMobile(request.getCustomerMobile())
                .customerEmail(request.getCustomerEmail())
                .address1(request.getAddress1())
                .address2(request.getAddress2())
                .build();

        Account account = Account.builder()
                .accountType(request.getAccountType())
                .availableBalance(BigDecimal.ZERO)
                .customer(customer)
                .build();

        customer.getAccounts().add(account);
        Customer savedCustomer = customerRepository.save(customer);

        return CreateAccountResponse.builder()
                .customerNumber(savedCustomer.getCustomerNumber())
                .transactionStatusCode(AppConstants.TRANS_CODE_201)
                .transactionStatusDescription(AppConstants.MSG_CUSTOMER_CREATED)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerInquiryResponse getCustomerInquiry(Long customerNumber) {
        Customer customer = customerRepository.findById(customerNumber)
                .orElseThrow(() -> new CustomerNotFoundException(AppConstants.MSG_CUSTOMER_NOT_FOUND));

        List<AccountDetailsDto> accountsDto = customer.getAccounts().stream()
                .map(acc -> AccountDetailsDto.builder()
                        .accountNumber(acc.getAccountNumber())
                        .accountType(acc.getAccountType().getDescription())
                        .availableBalance(acc.getAvailableBalance())
                        .build())
                .collect(Collectors.toList());

        return CustomerInquiryResponse.builder()
                .customerNumber(String.valueOf(customer.getCustomerNumber()))
                .customerName(customer.getCustomerName())
                .customerMobile(customer.getCustomerMobile())
                .customerEmail(customer.getCustomerEmail())
                .address1(customer.getAddress1())
                .address2(customer.getAddress2())
                .savings(accountsDto)
                .transactionStatusCode(AppConstants.TRANS_CODE_302)
                .transactionStatusDescription(AppConstants.MSG_CUSTOMER_NOT_FOUND)
                .build();
    }
}
