package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RespCode {

  SUCCESS(200, "成功"),
  SYSTEM_ERROR(500, "系統錯誤"),
  ;
  private int errorCode;
  private String errorMsg;
}
