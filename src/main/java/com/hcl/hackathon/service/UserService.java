package com.hcl.hackathon.service;

import com.hcl.hackathon.model.OrderDetails;

import java.util.List;

/**
 * The interface User service.
 */
public interface UserService {
    /**
     * Find orders by user id list.
     *
     * @param userId the user id
     * @return the list
     */
    List<OrderDetails> findOrdersByUserId(long userId);
}
