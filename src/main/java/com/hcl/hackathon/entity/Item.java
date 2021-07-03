package com.hcl.hackathon.entity;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the item database table.
 */
@Entity
@NamedQuery(name="Item.findAll", query="SELECT i FROM Item i")
@Data
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="item_id")
	private Long itemId;

	@Column(name="item_name")
	private String itemName;

	@Column(name="item_type")
	private String itemType;

	private Double price;

	//bi-directional many-to-one association to OrderItem
	@OneToMany(mappedBy="item")
	private List<OrderItem> orderItems;



}