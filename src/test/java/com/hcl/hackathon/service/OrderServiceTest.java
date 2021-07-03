package com.hcl.hackathon.service;

import com.hcl.hackathon.entity.Item;
import com.hcl.hackathon.entity.OrderInfo;
import com.hcl.hackathon.entity.OrderItem;
import com.hcl.hackathon.mapper.OrderMapper;
import com.hcl.hackathon.model.ItemDTO;
import com.hcl.hackathon.model.OrderDTO;
import com.hcl.hackathon.model.OrderInfoDTO;
import com.hcl.hackathon.model.OrderItemDTO;
import com.hcl.hackathon.repository.OrderRepository;
import com.hcl.hackathon.service.impl.OrderServiceImpl;
import com.hcl.hackathon.util.OrderTestUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static com.hcl.hackathon.util.OrderTestUtil.getOrderInfoDTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

	@Mock
	OrderRepository orderRepository;

	@InjectMocks
	OrderServiceImpl orderService;

	@Mock
	OrderMapper orderMapper;

	@Test
	public void createOrderTest() {
		OrderInfoDTO orderInfoDTO= getOrderInfoDTO();
		OrderInfo orderInfo=getOrderInfo();
		when(orderMapper.fromVoToEntity(Mockito.any())).thenReturn(getOrderInfo());
		when(orderRepository.save(Mockito.any())).thenReturn(getOrderInfo());
		OrderDTO orderDTO=orderService.createOrder(orderInfoDTO);
		verify(orderRepository).save(orderInfo);
		assertThat(orderDTO).isNotNull();
		assertThat(orderDTO.getOrderNo()).isEqualTo("ORD12345");
	}

	@Test(expected = RuntimeException.class)
	public void createCustomerInternalServerTest() {
		OrderInfoDTO orderInfoDTO= getOrderInfoDTO();
		OrderInfo orderInfo=getOrderInfo();
		OrderDTO orderDTO=orderService.createOrder(orderInfoDTO);
		verify(orderRepository).save(orderInfo);
		assertThat(orderDTO).isNull();

	}

	@Test
	public void findOrdersByOrderStatusTest() {
		OrderInfo orderInfo=getOrderInfo();
		List<OrderInfoDTO> orderInfoDTOList = new ArrayList<>();
		orderInfoDTOList.add(getOrderInfoDTO());
		List<OrderInfo> orderInfoList = new ArrayList<>();
		orderInfoList.add(getOrderInfo());
		when(orderMapper.mapOrderInfoDetails(orderInfoList)).thenReturn(orderInfoDTOList);
		when(orderRepository.findByOrderStatus("completed")).thenReturn(orderInfoList);
		List<OrderInfoDTO> orderInfoDTOS=orderService.findOrdersByOrderStatus("ORD12345", "completed");
		verify(orderRepository).findByOrderStatus("completed");
		assertThat(orderInfoDTOS).isNotNull();
		assertThat(orderInfoList.get(0).getOrderStatus()).isEqualTo("completed");
	}

	@Test(expected = RuntimeException.class)
	public void findByOrderStatusInternalServerTest() {
		when(orderRepository.findByOrderStatusAndOrderNo("completed","ORD12345")).thenThrow(RuntimeException.class);
		List<OrderInfoDTO> orderInfoDTOS=orderService.findOrdersByOrderStatus("ORD12345", "completed");
		verify(orderRepository).findByOrderStatusAndOrderNo("completed","ORD12345");
		assertThat(orderInfoDTOS).isNull();
	}



	private OrderInfo getOrderInfo() {
		OrderInfo orderInfo=new OrderInfo();
		orderInfo.setOrderNo("ORD12345");
		orderInfo.setOrderStatus("completed");
		orderInfo.setOrderId(1);
		orderInfo.setUserId(123l);
		orderInfo.setTotalAmount(123.0);
		String input = "2007-11-11 12:13:14" ;
		java.sql.Timestamp ts = java.sql.Timestamp.valueOf( input ) ;
		orderInfo.setCreateTime(ts);
		orderInfo.setOrderItems(createOrderItemsEntity());
		return orderInfo;
	}

	private List<OrderItem> createOrderItemsEntity() {
		List<OrderItem> orderItems=new ArrayList<>();
		OrderItem orderItem=new OrderItem();
		orderItem.setItem(createItemEntity());
		orderItem.setPrice(123.0);
		orderItem.setQuantity("Quantity");
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
