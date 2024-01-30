package com.example.demo.controller;

import com.example.demo.dto.RespCode;
import com.example.demo.dto.RespEntity;
import com.example.demo.entity.Order;
import com.example.demo.service.OrderService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//3. 請設計一個訂單訂購api，會員可以訂購產品。
//4. 請設計一個訂單查詢api，會員可以根據訂單編號或產品名稱或購買日期做分頁查詢。
//5. 請設計一個訂單統計api，統計訂單數大於n的會員資料。
@Log4j2
@RestController
@RequestMapping("/order")
public class OrderController {

  @Autowired
  private OrderService orderService;

  @PostMapping("/prod")
  public RespEntity orderByUser(
      @RequestParam("userId") Long userId,
      @RequestParam("prodName") String prodName
  ) {
    var order = new Order();
    order.setUserId(userId);
    order.setProdName(prodName);
    try {
      return RespEntity.success(orderService.create(order));
    } catch (Exception e) {
      log.error("create order error", e);
      return RespEntity.error(RespCode.SYSTEM_ERROR);
    }
  }

  @GetMapping("/query")
  public RespEntity queryUser(
      @RequestParam("orderId") Long orderId,
      @RequestParam("prodName") String prodName,
      @RequestParam("startTime") String startTime,
      @RequestParam("endTime") String endTime,
      @RequestParam("page") int page,
      @RequestParam("pageSize") int pageSize
  ) {
    try {
      var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
      return RespEntity.success(
          orderService.queryByPage(orderId, prodName, LocalDateTime.parse(startTime, formatter),
              LocalDateTime.parse(endTime, formatter), page, pageSize));
    } catch (Exception e) {
      log.error("query order error", e);
      return RespEntity.error(RespCode.SYSTEM_ERROR);
    }
  }

  @GetMapping("/user/count")
  public RespEntity findUserByOrderCount(
      @RequestParam("orderCount") int orderCount
  ) {
    try {
      return RespEntity.success(orderService.findUserByOrderCount(orderCount));
    } catch (Exception e) {
      log.error("find user by order count error", e);
      return RespEntity.error(RespCode.SYSTEM_ERROR);
    }
  }
}
