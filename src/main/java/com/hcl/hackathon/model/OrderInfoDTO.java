package com.hcl.hackathon.model;

import io.swagger.v3.oas.annotations.media.Schema;
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

@Data
public class OrderInfoDTO implements Serializable {
	private static final long serialVersionUID = 1L;


	private Integer orderId;


	private String orderNo;

	@Schema(description = "order status",
			example = "Order", required = false)
	private String orderStatus;

	@Schema(description = "total Amount",
			example = "100.9", required = false)
	private Double totalAmount;

	@Schema(description = "user Id",
			example = "user Id", required = false)
	private Integer userId;

	@Schema(description = "order Items")
	private List<OrderItemDTO> orderItems;


}