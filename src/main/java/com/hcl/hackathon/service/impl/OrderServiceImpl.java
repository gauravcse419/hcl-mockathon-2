package com.hcl.hackathon.service.impl;

import com.hcl.hackathon.entity.OrderInfo;
import com.hcl.hackathon.mapper.OrderMapper;
import com.hcl.hackathon.model.OrderDTO;
import com.hcl.hackathon.model.OrderInfoDTO;
import com.hcl.hackathon.repository.OrderRepository;
import com.hcl.hackathon.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
        logger.info("OrderService createOrder{}", orderInfoDTO);
        OrderDTO orderDTO = new OrderDTO();
        OrderInfo orderInfo = orderRepository.save(orderMapper.fromVoToEntity(orderInfoDTO));
        orderDTO.setOrderNo(orderInfo.getOrderNo());
        orderDTO.setOrderStatus("Your Oder Placed Sucussfully");
        return orderDTO;
    }

    /**
     * Method to get orders by order status and order no
     *
     * @param orderNo
     * @param orderStatus
     * @return list of orders
     */
    @Override
    public List<OrderInfoDTO> findOrdersByOrderStatus(String orderNo, String orderStatus) {
        List<OrderInfoDTO> orderInfoDTOS = null;
        List<OrderInfo> orderInfoDetail = null;


        orderInfoDetail = orderRepository.findByOrderStatus(orderStatus);
        orderInfoDTOS = orderMapper.mapOrderInfoDetails(orderInfoDetail);

        if (orderNo != null) {
            orderInfoDetail = orderRepository.findByOrderStatusAndOrderNo(orderStatus, orderNo);
            orderInfoDTOS = orderMapper.mapOrderInfoDetails(orderInfoDetail);
        }

        return orderInfoDTOS;
    }
    
    @Override
    public void updateOrderStatus(String orderNo, OrderDTO orderDTO) {
         // Do update operation.
    }
}
