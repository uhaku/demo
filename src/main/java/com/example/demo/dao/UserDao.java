package com.example.demo.dao;

import com.example.demo.entity.User;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

//		2. 請設計新增/刪除/修改/查詢(單一以及分頁)會員功能api。
public interface UserDao extends CrudRepository<User, Long> {

  List<User> findByNameAndPhoneAndEmail(String name, String phone, String email, Pageable pageable);

  @Query(value = " SELECT * FROM user u JOIN (SELECT count(1) as count, o.user_id as uid FROM order o GROUP BY o.user_id) oc on u.id = oc.uid WHERE oc.count >= :orderCount", nativeQuery = true)
  List<User> findByOrderCount(int orderCount);

}