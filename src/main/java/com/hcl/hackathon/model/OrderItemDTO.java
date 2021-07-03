package com.hcl.hackathon.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.io.Serializable;


/**
 * The persistent class for the order_item database table.
 * 
 */

@Data
public class OrderItemDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(description = "order Item Id",
			example = "1234555544", required = false)
	private Integer orderItemd;

	@Schema(description = "price",
			example = "200.0", required = false)
	private Double price;

	@Schema(description = "quantity",
			example = "5", required = false)
	private int quantity;

	@Schema(description = "item")
	private ItemDTO item;


}