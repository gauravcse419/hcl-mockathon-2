package com.hcl.hackathon.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the order_item database table.
 * 
 */

@Getter
@Setter
public class OrderItemDTO implements Serializable {
	private static final long serialVersionUID = 1L;


	private Integer orderItemd;

	private Integer price;

	private String quantity;

	private ItemDTO item;

	private OrderInfoDTO orderInfo;


}