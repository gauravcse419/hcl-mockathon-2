/**
 * Documenting Spring Boot REST API with SpringDoc + OpenAPI 3 (https://www.dariawan.com)
 * Copyright (C) 2019 Dariawan <hello@dariawan.com>
 *
 * Creative Commons Attribution-ShareAlike 4.0 International License
 *
 * Under this license, you are free to:
 * # Share - copy and redistribute the material in any medium or format
 * # Adapt - remix, transform, and build upon the material for any purpose,
 *   even commercially.
 *
 * The licensor cannot revoke these freedoms
 * as long as you follow the license terms.
 *
 * License terms:
 * # Attribution - You must give appropriate credit, provide a link to the
 *   license, and indicate if changes were made. You may do so in any
 *   reasonable manner, but not in any way that suggests the licensor
 *   endorses you or your use.
 * # ShareAlike - If you remix, transform, or build upon the material, you must
 *   distribute your contributions under the same license as the original.
 * # No additional restrictions - You may not apply legal terms or
 *   technological measures that legally restrict others from doing anything the
 *   license permits.
 *
 * Notices:
 * # You do not have to comply with the license for elements of the material in
 *   the public domain or where your use is permitted by an applicable exception
 *   or limitation.
 * # No warranties are given. The license may not give you all of
 *   the permissions necessary for your intended use. For example, other rights
 *   such as publicity, privacy, or moral rights may limit how you use
 *   the material.
 *
 * You may obtain a copy of the License at
 *   https://creativecommons.org/licenses/by-sa/4.0/
 *   https://creativecommons.org/licenses/by-sa/4.0/legalcode
 */
package com.hcl.hackathon.service.impl;

import com.hcl.hackathon.entity.Item;
import com.hcl.hackathon.entity.OrderInfo;
import com.hcl.hackathon.entity.OrderItem;
import com.hcl.hackathon.model.ItemDTO;
import com.hcl.hackathon.model.OrderInfoDTO;
import com.hcl.hackathon.model.OrderItemDTO;
import com.hcl.hackathon.repository.OrderRepository;

import com.hcl.hackathon.service.OrderService;
import com.hcl.hackathon.util.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    
    @Autowired
    private OrderRepository orderRepository;

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
        OrderMapper orderMapper = new OrderMapper();

            orderInfoDetail = orderRepository.findByOrderStatus(orderStatus);
            orderInfoDTOS = orderMapper.mapOrderInfoDetails(orderInfoDetail);

              if(orderNo != null){
             orderInfoDetail = orderRepository.findByOrderStatusAndOrderNo(orderStatus, orderNo);
            orderInfoDTOS = orderMapper.mapOrderInfoDetails(orderInfoDetail);
        }

        return  orderInfoDTOS;
    }


}
