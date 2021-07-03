package com.hcl.hackathon.util;

import com.hcl.hackathon.model.ItemDTO;
import com.hcl.hackathon.model.OrderInfoDTO;
import com.hcl.hackathon.model.OrderItemDTO;

import java.util.ArrayList;
import java.util.List;

public class OrderTestUtil {

    public static OrderInfoDTO getOrderInfoDTO() {
        OrderInfoDTO orderInfoDTO=new OrderInfoDTO();
        orderInfoDTO.setOrderId(1);
        orderInfoDTO.setOrderNo("ORD12345");
        orderInfoDTO.setOrderStatus("completed");
        orderInfoDTO.setTotalAmount(123.0);
        orderInfoDTO.setUserId(1);
        orderInfoDTO.setOrderItems(createOrderItems());
        return orderInfoDTO;
    }

    public static List<OrderItemDTO> createOrderItems() {
        List<OrderItemDTO> orderItemDTOS=new ArrayList<>();
        OrderItemDTO orderItemDTO=new OrderItemDTO();
        orderItemDTO.setPrice(123.0);
        orderItemDTO.setQuantity("setQuantity");
        orderItemDTO.setOrderItemd(123);
        orderItemDTO.setItem(createItems());
        orderItemDTOS.add(orderItemDTO);
        return orderItemDTOS;
    }

    public static ItemDTO createItems() {
        ItemDTO itemDTO=new ItemDTO();
        itemDTO.setItemId(1l);
        itemDTO.setItemName("name");
        itemDTO.setItemType("type");
        itemDTO.setPrice(123.0);
        return itemDTO;
    }
}
