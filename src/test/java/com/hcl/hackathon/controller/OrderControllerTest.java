package com.hcl.hackathon.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.hackathon.exception.ResourceNotFoundException;
import com.hcl.hackathon.model.OrderDTO;
import com.hcl.hackathon.model.OrderInfoDTO;
import com.hcl.hackathon.service.OrderService;
import com.hcl.hackathon.util.OrderTestUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    OrderService orderService;

    @Test
    public void createOrderAPI() throws Exception {

        OrderDTO orderDTO=getOrder();
        when(orderService.createOrder(Mockito.any())).thenReturn(orderDTO);
        mvc.perform(MockMvcRequestBuilders.post("/api/order")
                .content(asJsonString(OrderTestUtil.getOrderInfoDTO())).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.orderNo").exists())
         .andExpect(MockMvcResultMatchers.jsonPath("$.orderStatus").exists());

        verify(orderService).createOrder(Mockito.any());
    }

    private OrderDTO getOrder() {
        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setOrderNo("ORD1234555544");
        orderDTO.setOrderStatus("completed");
        return orderDTO;
    }

    @Test
    public void createOrderWithInternalServer() throws Exception {

        when(orderService.createOrder(Mockito.any())).thenThrow(RuntimeException.class);
        mvc.perform(MockMvcRequestBuilders.post("/api/order")
                .content(asJsonString(null)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isInternalServerError());
    }


    @Test
    public void updateOrderStatus() throws Exception {
        Mockito.doNothing().when(orderService).updateOrderStatus(Mockito.anyString(),Mockito.any());
        mvc.perform(MockMvcRequestBuilders.put("/api/order/{orderNumber}", 1)
                .content(asJsonString(getOrder())).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		verify(orderService).updateOrderStatus(Mockito.anyString(),Mockito.any());

    }

   /* @Test
    public void updateOrderStatusWithBadRequest() throws Exception {

        Mockito.doNothing().when(orderService).updateOrderStatus(Mockito.any(), Mockito.anyString());
        mvc.perform(MockMvcRequestBuilders.put("/api/order/null", 1)
                .content(asJsonString(new OrderDTO(null, "completed"))).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

    }*/

    @Test
    public void updateOrderStatusWithInternalServer() throws Exception {

        Mockito.doThrow(RuntimeException.class).when(orderService).updateOrderStatus(Mockito.anyString(),Mockito.any());
        mvc.perform(MockMvcRequestBuilders.put("/api/order/ORD1234555544", 1)
                .content(asJsonString(getOrder())).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isInternalServerError());
		verify(orderService).updateOrderStatus(Mockito.anyString(),Mockito.any());

    }


    @Test
    public void getOrdersByOrderStatus() throws Exception {
        List<OrderInfoDTO> orderInfoDTOS=new ArrayList<>();
        OrderInfoDTO orderInfoDTO=new OrderInfoDTO();
        orderInfoDTO.setOrderId(1);
        orderInfoDTO.setOrderNo("ORD1234512");
        orderInfoDTOS.add(orderInfoDTO);
        when(orderService.findOrdersByOrderStatus(Mockito.anyString(),Mockito.anyString())).thenReturn(orderInfoDTOS);
        mvc.perform(MockMvcRequestBuilders.get("/api/orders?orderNo=ORD1234512&orderStatus=completed").accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].orderNo").value("ORD1234512"))
          .andExpect(MockMvcResultMatchers.jsonPath("$[0].orderId").value(1));
		verify(orderService).findOrdersByOrderStatus(Mockito.anyString(),Mockito.anyString());
    }

    @Test
    public void getOrdersByOrderStatus_notFound() throws Exception {
        when(orderService.findOrdersByOrderStatus(Mockito.anyString(),Mockito.anyString()))
                .thenThrow(ResourceNotFoundException.class);

        mvc.perform(MockMvcRequestBuilders.get("/api/orders?orderNo=ORD1234512&orderStatus=completed"))
                .andExpect(status().isNotFound());
		verify(orderService).findOrdersByOrderStatus(Mockito.anyString(),Mockito.anyString());

    }

    @Test
    public void getOrdersByOrderStatus_InternalServer() throws Exception {
        when(orderService.findOrdersByOrderStatus(Mockito.anyString(),Mockito.anyString()))
                .thenThrow(RuntimeException.class);

        mvc.perform(MockMvcRequestBuilders.get("/api/orders?orderNo=ORD1234512&orderStatus=completed"))
                .andExpect(status().isInternalServerError());
        verify(orderService).findOrdersByOrderStatus(Mockito.anyString(),Mockito.anyString());

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
