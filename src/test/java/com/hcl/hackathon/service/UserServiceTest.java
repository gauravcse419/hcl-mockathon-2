package com.hcl.hackathon.service;

import com.hcl.hackathon.entity.Item;
import com.hcl.hackathon.entity.OrderInfo;
import com.hcl.hackathon.entity.OrderItem;
import com.hcl.hackathon.exception.ResourceNotFoundException;
import com.hcl.hackathon.model.OrderDetails;
import com.hcl.hackathon.repository.UserRepository;
import com.hcl.hackathon.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@Mock
	UserRepository userRepository;

	@InjectMocks
	UserServiceImpl userServiceImpl;

	@Test
	public void getOrdersByUserIdTest() {
		List<OrderInfo> orderInfoList=new ArrayList<>();
		OrderInfo orderInfo=getOrderInfo();
		orderInfoList.add(orderInfo);
		Long userId=1l;
		when(userRepository.findByUserId(Mockito.anyLong())).thenReturn(orderInfoList);
		List<OrderDetails> orderDetails=userServiceImpl.findOrdersByUserId(userId);
		verify(userRepository).findByUserId(Mockito.anyLong());
		assertThat(orderDetails).isNotNull();
		assertThat(orderDetails.get(0).getOrderNo()).isEqualTo("ORD12345");

	}





	@Test(expected = ResourceNotFoundException.class)
	public void getOrdersByUserId_Not_foundTest() {
		Long userId=1l;
		when(userRepository.findByUserId(Mockito.anyLong())).thenThrow(ResourceNotFoundException.class);
		List<OrderDetails> orderDetails=userServiceImpl.findOrdersByUserId(userId);
		verify(userRepository).findByUserId(Mockito.anyLong());
		assertThat(orderDetails).isNull();
	}


	private OrderInfo getOrderInfo() {
		OrderInfo orderInfo=new OrderInfo();
		orderInfo.setOrderNo("ORD12345");
		orderInfo.setOrderStatus("completed");
		orderInfo.setOrderId(1);
		orderInfo.setUserId(123l);
		orderInfo.setTotalAmount(123.0);
		String input = "2007-11-11 12:13:14" ;
		Timestamp ts = Timestamp.valueOf( input ) ;
		orderInfo.setCreateTime(ts);
		orderInfo.setOrderItems(createOrderItemsEntity());
		return orderInfo;
	}

	private List<OrderItem> createOrderItemsEntity() {
		List<OrderItem> orderItems=new ArrayList<>();
		OrderItem orderItem=new OrderItem();
		orderItem.setItem(createItemEntity());
		orderItem.setPrice(123.0);
		orderItem.setQuantity(2);
		orderItem.setOrderItemd(1l);
		orderItems.add(orderItem);
		return orderItems;
	}

	private Item createItemEntity() {
		Item item=new Item();
		item.setItemName("ItemName");
		item.setItemType("type");
		item.setPrice(123.0);
		item.setItemId(1l);
		return item;
	}

}
