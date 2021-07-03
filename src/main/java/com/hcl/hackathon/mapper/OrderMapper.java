package com.hcl.hackathon.mapper;

import com.hcl.hackathon.entity.Item;
import com.hcl.hackathon.entity.OrderInfo;
import com.hcl.hackathon.entity.OrderItem;
import com.hcl.hackathon.model.ItemDTO;
import com.hcl.hackathon.model.OrderInfoDTO;
import com.hcl.hackathon.model.OrderItemDTO;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    UUID uuid = UUID.randomUUID();

    public OrderInfo fromVoToEntity(OrderInfoDTO from) {
        OrderInfo to = new OrderInfo();
        if(from != null) {
           to.setOrderNo(uuid.toString());
           to.setTotalAmount(from.getTotalAmount());
           to.setCreateTime(new Timestamp(new Date().getTime()));
           to.setOrderStatus(from.getOrderStatus());
           to.setOrderItems( from.getOrderItems().stream().map(this::modelToEntity).collect(Collectors.toList()));
        }
        return to;

    }

    private OrderItem modelToEntity(OrderItemDTO orderItemDTO) {
        OrderItem orderItem = new OrderItem();
        orderItem.setPrice(orderItemDTO.getPrice());
        orderItem.setQuantity(orderItemDTO.getQuantity());
        orderItem.setItem(fromVoToEntityItem(orderItemDTO.getItem()));
        return orderItem;
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

    public List<OrderInfoDTO> mapOrderInfoDetails(List<OrderInfo> orderInfoDetail) {
        return orderInfoDetail.stream().map(this::mapOrderDetailRespose).collect(Collectors.toList());
    }

    private OrderInfoDTO mapOrderDetailRespose(OrderInfo orderInfo) {
        OrderInfoDTO orderInfoDTO = new OrderInfoDTO();
        orderInfoDTO.setOrderId(orderInfo.getOrderId());
        orderInfoDTO.setOrderNo(orderInfo.getOrderNo());
        orderInfoDTO.setOrderStatus(orderInfo.getOrderStatus());
        orderInfoDTO.setOrderItems(mapOrderItem(orderInfo.getOrderItems()));
        return  orderInfoDTO;
    }

    private List<OrderItemDTO> mapOrderItem(List<OrderItem> orderItems) {
        return orderItems.stream().map(this::mapOrderItemDetailRespose).collect(Collectors.toList());

    }

    private OrderItemDTO mapOrderItemDetailRespose(OrderItem orderItem) {
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setQuantity(orderItem.getQuantity());
        orderItemDTO.setItem(mapItemDetail(orderItem.getItem()));
        return  orderItemDTO;
    }

    private ItemDTO mapItemDetail(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setItemId(item.getItemId());
        itemDTO.setItemName(item.getItemName());
        itemDTO.setItemType(item.getItemType());
        return  itemDTO;
    }
}
