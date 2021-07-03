package com.hcl.hackathon.service.impl;

import com.hcl.hackathon.entity.OrderInfo;
import com.hcl.hackathon.exception.OrderManagementException;
import com.hcl.hackathon.mapper.OrderMapper;
import com.hcl.hackathon.model.OrderDTO;
import com.hcl.hackathon.model.OrderInfoDTO;
import com.hcl.hackathon.repository.OrderRepository;
import com.hcl.hackathon.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderMapper orderMapper;

    @Override
    public OrderDTO createOrder(OrderInfoDTO orderInfoDTO) {
        OrderDTO orderDTO = new OrderDTO();
        OrderInfo orderInfo = orderRepository.save(orderMapper.fromVoToEntity(orderInfoDTO));
        if(orderInfo != null) {
            orderDTO.setOrderNo(orderDTO.getOrderNo());
            orderDTO.setOrderStatus("Your Oder Placed Sucussfully");
            return orderDTO;
        } else {
            throw new OrderManagementException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error Occur while saving the order");
        }
    }
}
