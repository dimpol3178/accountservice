package com.bank.account.service;

import com.bank.account.dto.AccountRequest;
import com.bank.account.dto.CreateAccountResponse;
import com.bank.account.dto.CustomerInquiryResponse;

public interface CustomerAccountService {
    CreateAccountResponse createCustomerAccount(AccountRequest request);
    CustomerInquiryResponse getCustomerInquiry(Long customerNumber);
}
