package com.hcl.hackathon.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the order_info database table.
 * 
 */

@Getter
@Setter
public class OrderDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(description = "order No",
			example = "ORD1234555544", required = false)
	private Integer orderNo;

	@Schema(description = "order status",
			example = "completed", required = false)
	private String orderStatus;


}