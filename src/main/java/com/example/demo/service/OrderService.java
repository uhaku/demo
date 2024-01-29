package com.example.demo.service;

import com.example.demo.dao.OrderDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.Order;
import com.example.demo.entity.User;
import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

//3. 請設計一個訂單訂購api，會員可以訂購產品。
//    4. 請設計一個訂單查詢api，會員可以根據訂單編號或產品名稱或購買日期做分頁查詢。
//    5. 請設計一個訂單統計api，統計訂單數大於n的會員資料。
@Service
@Slf4j
public class OrderService {

  @Autowired
  private OrderDao orderDao;
  @Autowired
  private UserDao userDao;

  public Order create(Order Order) {
    return orderDao.save(Order);
  }

  public List<Order> queryByPage(Long orderId, String prodName, LocalDateTime startTime, LocalDateTime endTime, int page, int pageSize) {
    Pageable pageable = PageRequest.of(page - 1, pageSize,
        Sort.by("id"));
    return orderDao.findByOrderIdAndProdNameAndOrderTime(orderId, prodName, startTime, endTime, pageable);
  }

  public List<User> findUserByOrderCount(int count){
    return userDao.findByOrderCount(count);
  }
}
