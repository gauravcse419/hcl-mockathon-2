package com.hcl.hackathon.entity;
import lombok.Data;
import java.sql.Timestamp;
import javax.persistence.*;
@Entity
@Table(name = "transaction_details")
@Data
public class TransactionDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="transaction_id",unique = true,nullable = false)
    private int transactionId;

    @Column(name="type")
    private String type;
    @Column(name="transaction_date")
    private Timestamp transactionDate;
    @Column(name="account_no")
    private Long accountNo;
    @Column(name="amount")
    private double amount;
    @Column(name="transaction_description")
    private String transactionDescription;
    @Column(name="balance_amount")
    private double balanceAmount;

}