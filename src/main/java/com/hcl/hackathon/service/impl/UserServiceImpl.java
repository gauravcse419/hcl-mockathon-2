package com.hcl.hackathon.service.impl;

import com.hcl.hackathon.entity.OrderInfo;
import com.hcl.hackathon.exception.ResourceNotFoundException;
import com.hcl.hackathon.model.OrderDetails;
import com.hcl.hackathon.repository.UserRepository;
import com.hcl.hackathon.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The type User service.
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * The User repository.
     */
    @Autowired
    UserRepository userRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Find orders by user id list.
     *
     * @param userId the user id
     * @return the list
     */
    @Override
    public List<OrderDetails> findOrdersByUserId(long userId) {


        logger.debug("Started UserServiceImpl {}",userId);


        List<OrderInfo> orderRes =  userRepository.findByUserId(userId);
        logger.debug("Response UserServiceImpl {}", orderRes);
        if (orderRes.isEmpty()){
            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND.value(),"No Order has been booked under this user");
        }
        return orderRes.stream().map(this::getOrders).collect(Collectors.toList());

    }

    private OrderDetails getOrders(OrderInfo orderInfo) {
        OrderDetails orderDetail = new OrderDetails();
        orderDetail.setOrderNo(orderInfo.getOrderNo());
        orderDetail.setOrderStatus(orderInfo.getOrderStatus());
        orderDetail.setOrderDesc("Order has been Provisioned");
        return orderDetail;
    }
}
