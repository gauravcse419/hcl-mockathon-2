package com.hcl.hackathon.entity;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the order_info database table.
 * 
 */
@Entity
@Table(name="order_info")

@Data
public class OrderInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="order_id")
	private Long orderId;

	@Column(name="create_time")
	private Timestamp createTime;

	@Column(name="order_no")
	private String orderNo;

	@Column(name="order_status")
	private String orderStatus;

	@Column(name="total_amount")
	private Double totalAmount;

	@Column(name="user_id")
	private Long userId;

	//bi-directional many-to-one association to OrderItem
	@OneToMany(mappedBy="orderInfo")
	private List<OrderItem> orderItems;


}