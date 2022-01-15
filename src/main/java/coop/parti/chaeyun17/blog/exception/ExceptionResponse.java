package coop.parti.chaeyun17.blog.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ExceptionResponse {

  private String message;
  private Object description;
  private LocalDateTime createdDate;
  private HttpStatus httpStatus;

  @Builder
  public ExceptionResponse(HttpStatus httpStatus, String message, Object description, LocalDateTime createdDate) {
    this.httpStatus = httpStatus;
    this.message = message;
    this.description = description;
    this.createdDate = createdDate;
  }

  public ExceptionResponse(AppException appException) {
    this.message = appException.getErrorCode().getMessage();
    this.httpStatus = appException.getErrorCode().getHttpStatus();
    this.description = appException.getDescription();
    this.createdDate = LocalDateTime.now();
  }

  public HttpStatus getHttpStatus() {
    return this.httpStatus;
  }

  public String getMessage() {
    return message;
  }

  public Object getDescription() {
    return description;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }
}
