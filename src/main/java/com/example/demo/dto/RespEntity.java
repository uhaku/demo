package com.example.demo.dto;

public class RespEntity<T> {
  public static final RespEntity SUCCESS_RESP;
  public static final Integer SUCCESSCODE;
  public T attachment;
  public int status;
  public String message;
  public String parameters;

  public RespEntity(int status, String message) {
    this.status = status;
    this.message = message;
  }

  public RespEntity(T attachment, int status, String message) {
    this.attachment = attachment;
    this.status = status;
    this.message = message;
  }

  public RespEntity(T attachment, int status) {
    this.attachment = attachment;
    this.status = status;
  }

  public RespEntity(int status, String message, String parameters) {
    this.parameters = parameters;
    this.status = status;
    this.message = String.format(message, parameters);
  }

  public static <T> RespEntity<T> success(Object object) {
    return new RespEntity(object, SUCCESSCODE);
  }

  public static RespEntity error(RespCode respCode) {
    return new RespEntity(respCode.getErrorCode(), respCode.getErrorMsg());
  }

  public String toString() {
    return "RespEntity{attachment=" + this.attachment + ", status=" + this.status + ", message='" + this.message + ", parameters='" + this.parameters + '\'' + '}';
  }

  public RespEntity() {
  }

  public T getAttachment() {
    return this.attachment;
  }

  public void setAttachment(T attachment) {
    this.attachment = attachment;
  }

  public int getStatus() {
    return this.status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getParameters() {
    return this.parameters;
  }

  public void setParameters(String parameters) {
    this.parameters = parameters;
  }

  static {
    SUCCESS_RESP = new RespEntity(RespCode.SUCCESS.getErrorCode(), RespCode.SUCCESS.getErrorMsg());
    SUCCESSCODE = 200;
  }
}
