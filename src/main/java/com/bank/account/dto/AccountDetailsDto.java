package com.bank.account.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDetailsDto {

    @JsonProperty("accountNumber ")
    private Long accountNumber;

    private String accountType;
    private BigDecimal availableBalance;
}