package com.hcl.hackathon.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the order_info database table.
 * 
 */

@Getter
@Setter
public class OrderInfoDTO implements Serializable {
	private static final long serialVersionUID = 1L;


	private Integer orderId;


	private Timestamp createTime;

	private Integer orderNo;


	private String orderStatus;


	private Integer totalAmount;


	private Integer userId;


	private List<OrderItemDTO> orderItems;


}