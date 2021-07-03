package com.hcl.hackathon.repository;

import com.hcl.hackathon.entity.OrderInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OrderRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	OrderRepository repository;
	
	@Test
	public void should_find_no_order_if_repository_is_empty() {
		Iterable<OrderInfo> orderInfo = repository.findAll();

		assertThat(orderInfo).isEmpty();
	}
	@Test
	public void should_store_a_order() {

		OrderInfo orderInfoResponse = repository.save(getOrderInfo());

		assertThat(orderInfoResponse).hasFieldOrPropertyWithValue("orderNo", "ORD1234556");
		assertThat(orderInfoResponse).hasFieldOrPropertyWithValue("orderStatus", "completed");
	}
	
	@Test
	public void should_delete_all_orders() {
		entityManager.persist(getOrderInfo());
		entityManager.persist(getOrderInfo());

		repository.deleteAll();

		assertThat(repository.findAll()).isEmpty();
	}
	public OrderInfo getOrderInfo(){
		OrderInfo orderInfo=new OrderInfo();
		orderInfo.setOrderNo("ORD1234556");
		orderInfo.setOrderStatus("completed");
		orderInfo.setTotalAmount(123.00);
		orderInfo.setUserId(1l);
		return orderInfo;

}
	@Test
	public void should_find_all_orders() {

		entityManager.persist(getOrderInfo());

		entityManager.persist(getOrderInfo());


		entityManager.persist(getOrderInfo());


		assertThat(repository.findAll()).isNotEmpty();
	}

	@Test
	public void should_find_order_by_id() {
		entityManager.persist(getOrderInfo());


		OrderInfo orderInfo=entityManager.persist(getOrderInfo());

		OrderInfo foundOrder = repository.findById(orderInfo.getOrderId()).orElse(null);

		assertThat(foundOrder.getOrderNo()).isEqualTo(getOrderInfo().getOrderNo());
	}
	


}
