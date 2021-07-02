package com.hcl.hackathon.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
@Entity
@Table(name = "customer_details")
@Data
public class CustomerDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id",unique = true,nullable = false)
    private int customerId;
    @Column(name="customer_name")
    private String customerName;
    @Column(name="phoneNo")
    private String phoneNo;
    @Column(name="emailId")
    private String emailId;
    @Column(name="date")
    private Timestamp Date;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private List<AccountDetails> accountDetails;

}