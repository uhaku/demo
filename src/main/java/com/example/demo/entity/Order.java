package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

//3. 請設計一個訂單訂購api，會員可以訂購產品。
//    4. 請設計一個訂單查詢api，會員可以根據訂單編號或產品名稱或購買日期做分頁查詢。
//    5. 請設計一個訂單統計api，統計訂單數大於n的會員資料。
@Data
@Entity
@Table(name = "order")
public class Order {
  @Id
  @GeneratedValue(
      strategy = GenerationType.IDENTITY
  )
  private Long id;
  private Long userId;
  private String prodName;
  private LocalDateTime orderTime;

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
