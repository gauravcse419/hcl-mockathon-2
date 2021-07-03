# hcl-mockathon-2



openapi: 3.0.1
info:
  title: Order Application API
  description: This is a sample Spring Boot RESTful service using springdoc-openapi
    and OpenAPI 3.
servers:
- url: http://localhost:9091
  description: Generated server url
tags:
- name: users
  description: the user API
- name: order
  description: The Order API
paths:
  /api/order:
    post:
      tags:
      - order
      summary: Add a new Order
      operationId: createOrder
      requestBody:
        description: Order to add. Cannot null or empty.
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/OrderInfoDTO'
          application/xml:
            schema:
              $ref: '#/components/schemas/OrderInfoDTO'
        required: true
      responses:
        500:
          description: default response
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/ErrorResponse'
        404:
          description: default response
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/ErrorResponse'
        409:
          description: Order already exists
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/OrderDTO'
        400:
          description: Invalid input
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/OrderDTO'
        200, description = order created:
          description: default response
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/OrderDTO'
  /api/orders:
    get:
      tags:
      - order
      summary: 'Find order by Order status '
      description: Returns a order List
      operationId: findOrdersByOrderStatus
      parameters:
      - name: orderStatus
        in: query
        description: orderStatus of the order to be obtained. Cannot be empty.
        required: true
        schema:
          type: string
      - name: orderNo
        in: query
        required: false
        schema:
          type: string
      responses:
        500:
          description: default response
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/ErrorResponse'
        404:
          description: orders not found
          content:
            application/json:
              schema:
                type: array
                items:
                  $ref: '#/components/schemas/OrderInfoDTO'
            application/xml:
              schema:
                type: array
                items:
                  $ref: '#/components/schemas/OrderInfoDTO'
        200:
          description: successful operation
          content:
            application/json:
              schema:
                type: array
                items:
                  $ref: '#/components/schemas/OrderInfoDTO'
            application/xml:
              schema:
                type: array
                items:
                  $ref: '#/components/schemas/OrderInfoDTO'
  /api/order/{orderNumber}:
    put:
      tags:
      - order
      summary: Update an existing Order status
      operationId: updateOrderStatus
      parameters:
      - name: orderNumber
        in: path
        description: Id of the Order status to be update. Cannot be empty.
        required: true
        schema:
          type: string
      requestBody:
        description: Order to update. Cannot null or empty.
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/OrderDTO'
          application/xml:
            schema:
              $ref: '#/components/schemas/OrderDTO'
        required: true
      responses:
        500:
          description: default response
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/ErrorResponse'
        404:
          description: Contact not found
        405:
          description: Validation exception
        400:
          description: Invalid ID supplied
        200:
          description: successful operation
  /api/v1/orders/{userId}:
    get:
      tags:
      - users
      summary: Find orders by UserId
      description: Returns a order List
      operationId: findOrdersByUserId
      parameters:
      - name: userId
        in: path
        description: Id of the user to be obtained. Cannot be empty.
        required: true
        schema:
          type: integer
          format: int64
      responses:
        500:
          description: default response
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/ErrorResponse'
        404:
          description: order not found
          content:
            application/json:
              schema:
                type: array
                items:
                  $ref: '#/components/schemas/OrderDetails'
        200:
          description: successful operation
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/OrderInfoDTO'
components:
  schemas:
    ErrorResponse:
      type: object
      properties:
        errorCode:
          type: integer
          format: int32
        errorMessage:
          type: string
    ItemDTO:
      type: object
      properties:
        itemId:
          type: integer
          format: int64
        itemName:
          maxLength: 50
          minLength: 0
          type: string
          description: Item Name
          example: Veg pizza comb1
        itemType:
          maxLength: 20
          minLength: 0
          type: string
          description: Item Type
          example: Veg
        price:
          maximum: 20
          minimum: 0
          type: number
          description: price
          format: double
          example: 20.0
      description: item
    OrderInfoDTO:
      type: object
      properties:
        orderId:
          type: integer
          description: order Id
          format: int32
          example: 1
        orderNo:
          type: string
          description: order No
          example: ORD123445555
        orderStatus:
          type: string
          description: order status
          example: Order
        totalAmount:
          type: number
          description: total Amount
          format: double
          example: 100.9
        userId:
          type: integer
          description: user Id
          format: int32
        orderItems:
          type: array
          description: order Items
          items:
            $ref: '#/components/schemas/OrderItemDTO'
    OrderItemDTO:
      type: object
      properties:
        orderItemd:
          type: integer
          description: order Item Id
          format: int32
          example: 1234555544
        price:
          type: number
          description: price
          format: double
          example: 200.0
        quantity:
          type: string
          description: quantity
          example: quantity
        item:
          $ref: '#/components/schemas/ItemDTO'
      description: order Items
    OrderDTO:
      type: object
      properties:
        orderNo:
          type: string
          description: order No
          example: ORD1234555544
        orderStatus:
          type: string
          description: order status
          example: completed
    OrderDetails:
      type: object
      properties:
        orderNo:
          type: string
          description: order No
          example: ORD123445555
        orderStatus:
          type: string
          description: order status
          example: completed
        orderDesc:
          type: string
          description: order Description
          example: Order is provisioned
