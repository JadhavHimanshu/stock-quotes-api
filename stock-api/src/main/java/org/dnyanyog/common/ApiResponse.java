package org.dnyanyog.common;

public class ApiResponse<T> {
  private String status;
  private String message;
  private T data;

  public ApiResponse(ResponseCode code, T data) {
    this.status = code.getCode();
    this.message = code.getMessage();
    this.data = data;
  }

  public String getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }

  public T getData() {
    return data;
  }
}
