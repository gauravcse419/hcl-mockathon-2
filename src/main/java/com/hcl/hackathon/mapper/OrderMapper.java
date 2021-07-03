package com.hcl.hackathon.mapper;

import com.hcl.hackathon.entity.Item;
import com.hcl.hackathon.entity.OrderInfo;
import com.hcl.hackathon.entity.OrderItem;
import com.hcl.hackathon.model.ItemDTO;
import com.hcl.hackathon.model.OrderInfoDTO;
import com.hcl.hackathon.model.OrderItemDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

@Component
public class OrderMapper {

    public OrderInfo fromVoToEntity(OrderInfoDTO from) {
        OrderInfo to = new OrderInfo();
        if(from != null) {
           to.setTotalAmount(from.getTotalAmount());
           to.setCreateTime(new Timestamp(new Date().getTime()));
           to.setOrderStatus(from.getOrderStatus());
           to.setOrderItems(fromVoToEntityOrderItem(from.getOrderItems()));
        }
        return to;

    }

    private List<OrderItem> fromVoToEntityOrderItem(List<OrderItemDTO> orderItems) {
        List<OrderItem> orderItemList = new ArrayList<>();
        if(orderItems.size()> 0) {
            orderItems.forEach(orderItemDTO -> {
                OrderItem orderItem = new OrderItem();
                orderItem.setPrice(orderItemDTO.getPrice());
                orderItem.setQuantity(orderItemDTO.getQuantity());
                orderItem.setItem(fromVoToEntityItem(orderItemDTO.getItem()));
                orderItemList.add(orderItem);
            });
        }
        return orderItemList;
    }

    private Item fromVoToEntityItem(ItemDTO from) {
        Item to = new Item();
        if(from != null) {
            to.setItemName(from.getItemName());
            to.setItemType(from.getItemName());
            to.setPrice(from.getPrice());
        }
        return to;

    }
}
