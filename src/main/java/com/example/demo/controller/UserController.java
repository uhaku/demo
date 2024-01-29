package com.example.demo.controller;

import com.example.demo.dto.RespCode;
import com.example.demo.dto.RespEntity;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/create")
	public RespEntity createUser(
			@RequestParam("name") String name,
			@RequestHeader("phone") String phone,
			@RequestHeader("email") String email
	) {
		var user = new User();
		user.setName(name);
		user.setPhone(phone);
		user.setEmail(email);
		try {
			return RespEntity.success(userService.create(user));
		} catch (Exception e) {
			log.error("create user error", e);
			return RespEntity.error(RespCode.SYSTEM_ERROR);
		}
	}

	@PostMapping("/update")
	public RespEntity updateUser(
			@RequestParam("id") Long id,
			@RequestParam("name") String name,
			@RequestHeader("phone") String phone,
			@RequestHeader("email") String email
	) {
		var updateUser = new User();
		updateUser.setId(id);
		updateUser.setName(name);
		updateUser.setPhone(phone);
		updateUser.setEmail(email);
		try {
			return RespEntity.success(userService.update(updateUser));
		} catch (Exception e) {
			log.error("update user error", e);
			return RespEntity.error(RespCode.SYSTEM_ERROR);
		}
	}

	@PostMapping("/delete")
	public RespEntity deleteUser(
			@RequestParam("id") Long id
	) {
		try {
			return RespEntity.success(userService.delete(id));
		} catch (Exception e) {
			log.error("delete user error", e);
			return RespEntity.error(RespCode.SYSTEM_ERROR);
		}
	}

	@GetMapping("/query")
	public RespEntity queryUser(
			@RequestParam("name") String name,
			@RequestHeader("phone") String phone,
			@RequestHeader("email") String email,
			@RequestHeader("page") int page,
			@RequestHeader("pageSize") int pageSize
	) {
		try {
			return RespEntity.success(userService.queryByPage(name,phone,email,page,pageSize));
		} catch (Exception e) {
			log.error("query user error", e);
			return RespEntity.error(RespCode.SYSTEM_ERROR);
		}
	}

	@GetMapping("/get")
	public RespEntity getUser(
			@RequestParam("id") Long id
	) {
		try {
			return RespEntity.success(userService.getById(id));
		} catch (Exception e) {
			log.error("get user error", e);
			return RespEntity.error(RespCode.SYSTEM_ERROR);
		}
	}
}
