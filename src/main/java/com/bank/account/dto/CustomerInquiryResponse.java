package com.bank.account.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerInquiryResponse {

    private String customerNumber;
    private String customerName;
    private String customerMobile;
    private String customerEmail;
    private String address1;
    private String address2;

    private List<AccountDetailsDto> savings;

    private int transactionStatusCode;
    private String transactionStatusDescription;
}