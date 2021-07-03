package com.hcl.hackathon.service.impl;

import com.hcl.hackathon.entity.OrderInfo;
import com.hcl.hackathon.exception.OrderManagementException;
import com.hcl.hackathon.mapper.OrderMapper;
import com.hcl.hackathon.model.OrderDTO;
import com.hcl.hackathon.model.OrderInfoDTO;
import com.hcl.hackathon.repository.OrderRepository;
import com.hcl.hackathon.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderMapper orderMapper;

    @Override
    public OrderDTO createOrder(OrderInfoDTO orderInfoDTO) {
        logger.info("OrderService createOrder{}",orderInfoDTO);
        OrderDTO orderDTO = new OrderDTO();
        OrderInfo orderInfoResponse=orderMapper.fromVoToEntity(orderInfoDTO);
        OrderInfo orderInfo = orderRepository.save(orderInfoResponse);
        if(orderInfo != null) {
            orderDTO.setOrderNo(orderInfo.getOrderNo());
            orderDTO.setOrderStatus("Your Oder Placed Sucussfully");
            return orderDTO;
        } else {
            throw new OrderManagementException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error Occur while saving the order");
        }
    }

    @Override
    public List<OrderInfoDTO> findOrdersByUserId(long userId) {
        return null;
    }

    @Override
    public List<OrderInfoDTO> findOrdersByOrderStatus(String orderNo, String orderStatus) {
        return null;
    }

    @Override
    public void updateOrderStatus(OrderDTO updateOrderStatus, String orderNumber) {

    }
}
