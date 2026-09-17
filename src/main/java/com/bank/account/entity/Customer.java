package com.bank.account.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerNumber;

    @Column(nullable = false, length = 50)
    private String customerName;

    @Column(nullable = false, length = 20)
    private String customerMobile;

    @Column(nullable = false, length = 50)
    private String customerEmail;

    @Column(nullable = false, length = 100)
    private String address1;

    @Column(length = 100)
    private String address2;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Account> accounts = new ArrayList<>();
}