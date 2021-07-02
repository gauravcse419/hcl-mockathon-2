package com.hcl.hackathon.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;
@Entity
@Table(name = "account_details")
@Data
public class AccountDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="account_no",unique = true,nullable = false)
    private Long accountNo;
    @Column(name="account_type")
    private String accountType;
    @Column(name="customer_id")
    private Long customerId;
    @Column(name="amount")
    private double amount;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_no")
    private Set<TransactionDetails> transactionDetailsList;

}