package com.example.demo.dao;

import com.example.demo.entity.Order;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

//3. 請設計一個訂單訂購api，會員可以訂購產品。
//    4. 請設計一個訂單查詢api，會員可以根據訂單編號或產品名稱或購買日期做分頁查詢。
//    5. 請設計一個訂單統計api，統計訂單數大於n的會員資料。
public interface OrderDao extends CrudRepository<Order, Long> {

  @Query(
      "SELECT * FROM Order o WHERE o.id = :orderId AND o.prodName = :prodName AND o.orderTime >= :startTime and o.orderTime < :endTime")
  List<Order> findByOrderIdAndProdNameAndOrderTime(Long orderId, String prodName, LocalDateTime startTime,
      LocalDateTime endTime, Pageable pageable);

}