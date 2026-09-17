package com.bank.account;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.bank.account.dto.*;
import com.bank.account.entity.AccountType;
import com.bank.account.service.CustomerAccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AccountserviceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateAccountSuccess() throws Exception {
        AccountRequest request = new AccountRequest();
        request.setCustomerName("Test");
        request.setCustomerMobile("09081234567");
        request.setCustomerEmail("test12345@gmail.com");
        request.setAddress1("test");
        request.setAddress2("test");
        request.setAccountType(AccountType.S);

        mockMvc.perform(post("/api/v1/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.transactionStatusCode").value(201))
                .andExpect(jsonPath("$.transactionStatusDescription").value("Customer account created"));
    }

    @Test
    public void testCreateAccountValidationError() throws Exception {
        AccountRequest request = new AccountRequest();
        request.setCustomerName("Test");
        request.setCustomerMobile("09081234567");
        // customerEmail left null intentionally
        request.setAddress1("test");
        request.setAccountType(AccountType.S);

        mockMvc.perform(post("/api/v1/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.transactionStatusCode").value(400))
                .andExpect(jsonPath("$.transactionStatusDescription").value("Email is required field"));
    }

    @Test
    public void testInquiryNotFound() throws Exception {
        mockMvc.perform(get("/api/v1/account/999999"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.transactionStatusCode").value(401))
                .andExpect(jsonPath("$.transactionStatusDescription").value("Customer not found"));
    }
}
