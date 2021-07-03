package com.hcl.hackathon.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the item database table.
 * 
 */

@Getter
@Setter
public class ItemDTO implements Serializable {
	private static final long serialVersionUID = 1L;


	private Integer itemId;

	@Schema(description = "Item Name",
			example = "Veg pizza comb1", required = false)
	@Size(max = 50)
	private String itemName;

	@Schema(description = "Item Type",
			example = "Veg", required = false)
	@Size(max = 20)
	private String itemType;

	@Schema(description = "price",
			example = "20.0", required = false)
	@Size(max = 20)
	private Double price;

	@Schema(description = "order Items")
	private List<OrderItemDTO> orderItems;



}