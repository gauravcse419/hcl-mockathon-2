package com.hcl.hackathon.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class OrderDetails {



    @Schema(description = "order No",
            example = "ORD123445555", required = false)
    private String orderNo;

    @Schema(description = "order status",
            example = "completed", required = false)
    private String orderStatus;

    @Schema(description = "order Description",
            example = "Order is provisioned", required = false)
    private String orderDesc;

}
