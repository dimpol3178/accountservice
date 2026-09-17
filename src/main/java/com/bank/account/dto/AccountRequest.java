package com.bank.account.dto;

import com.bank.account.entity.AccountType;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AccountRequest {

    @NotBlank(message = "Customer name is required field")
    @Size(max = 50, message = "Customer name must not exceed 50 characters")
    private String customerName;

    @NotBlank(message = "Customer mobile is required field")
    @Size(max = 20, message = "Customer mobile must not exceed 20 characters")
    private String customerMobile;

    @NotBlank(message = "Email is required field")
    @Email(message = "Email should be valid")
    @Size(max = 50, message = "Email must not exceed 50 characters")
    private String customerEmail;

    @NotBlank(message = "Address1 is required field")
    @Size(max = 100, message = "Address1 must not exceed 100 characters")
    private String address1;

    @Size(max = 100, message = "Address2 must not exceed 100 characters")
    private String address2;

    @NotNull(message = "Account type is required field")
    private AccountType accountType;
}
