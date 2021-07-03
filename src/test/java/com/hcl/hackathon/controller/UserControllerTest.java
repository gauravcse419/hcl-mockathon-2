package com.hcl.hackathon.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.hackathon.exception.ResourceNotFoundException;
import com.hcl.hackathon.model.OrderDTO;
import com.hcl.hackathon.model.OrderDetails;
import com.hcl.hackathon.model.OrderInfoDTO;
import com.hcl.hackathon.service.OrderService;
import com.hcl.hackathon.service.UserService;
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
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    UserService userService;

    @Test
    public void getOrdersByUserId() throws Exception {
        List<OrderDetails> orderInfoDTOS=new ArrayList<>();
        OrderDetails orderInfoDTO=new OrderDetails();
        orderInfoDTO.setOrderDesc("OrderDesc");
        orderInfoDTO.setOrderNo("ORD1234512");
        orderInfoDTO.setOrderStatus("completed");
        orderInfoDTOS.add(orderInfoDTO);
        when(userService.findOrdersByUserId(Mockito.anyLong())).thenReturn(orderInfoDTOS);
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/orders/1").accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].orderNo").value("ORD1234512"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].orderDesc").value("OrderDesc"));
        verify(userService).findOrdersByUserId(Mockito.anyLong());
    }

    @Test
    public void getOrdersByUserId_notFound() throws Exception {
        when(userService.findOrdersByUserId(Mockito.anyLong()))
                .thenThrow(ResourceNotFoundException.class);

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/orders/1"))
                .andExpect(status().isNotFound());
        verify(userService).findOrdersByUserId(Mockito.anyLong());

    }

    @Test
    public void getOrdersByUserId_InternalServer() throws Exception {
        when(userService.findOrdersByUserId(Mockito.anyLong()))
                .thenThrow(RuntimeException.class);

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/orders/1"))
                .andExpect(status().isInternalServerError());
        verify(userService).findOrdersByUserId(Mockito.anyLong());

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
