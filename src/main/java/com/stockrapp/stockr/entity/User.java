package com.stockrapp.stockr.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Document(collection = "users")
@ToString
public class User {

  @Id
  private String id;
  private String username;

  public User(String username) {
    this.username = username;
  }
}
