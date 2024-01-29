package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

//		2. 請設計新增/刪除/修改/查詢(單一以及分頁)會員功能api。
@Service
@Slf4j
public class UserService {

  @Autowired
  private UserDao userDao;

  public User create(User user) {
    return userDao.save(user);
  }

  public User update(User updateUser) throws Exception {
    var opt = userDao.findById(updateUser.getId());
    if (opt.isPresent()) {
      var user = opt.get();
      user.setName(updateUser.getName());
      user.setPhone(updateUser.getPhone());
      user.setEmail(updateUser.getEmail());
      return userDao.save(user);
    }
    throw new Exception("user not found");
  }

  public boolean delete(Long id) throws Exception {
    var opt = userDao.findById(id);
    if (opt.isPresent()) {
      userDao.delete(opt.get());
      return true;
    } else {
      throw new Exception("user not found");
    }
  }


  public List<User> queryByPage(String name, String phone, String email, int page, int pageSize) {
    Pageable pageable = PageRequest.of(page - 1, pageSize,
        Sort.by("id"));
    return userDao.findByNameAndPhoneAndEmail(name, phone, email, pageable);
  }

  public User getById(Long id) throws Exception {
    var opt = userDao.findById(id);
    if (opt.isPresent()) {
      return opt.get();
    } else {
      throw new Exception("user not found");
    }
  }
}
