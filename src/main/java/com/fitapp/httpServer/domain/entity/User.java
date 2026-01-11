package com.fitapp.httpServer.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fitapp.httpServer.domain.value_object.Password;

public class User {
  // fields / properties
  Long userId;
  String fname;
  String lname;
  @JsonIgnore
  Password password;
  int age;
  int bodyHeight;
  int bodyWeight;

  // NOTE: For Persisted Users -> ID is already created
  public User(String fname, String lname, Password password, int age, int bodyHeight, int bodyWeight) {
    this.fname = fname;
    this.lname = lname;
    this.password = password;
    this.age = age;
    this.bodyHeight = bodyHeight;
    this.bodyWeight = bodyWeight;
  }

  public User(long userId, String fname, String lname, Password password, int age, int bodyHeight, int bodyWeight) {
    this.userId = userId;
    this.fname = fname;
    this.lname = lname;
    this.password = password;
    this.age = age;
    this.bodyHeight = bodyHeight;
    this.bodyWeight = bodyWeight;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    if (this.userId != null) {
      throw new IllegalStateException("The id of the user already exists");
    }
    this.userId = userId;
  }

  public String getFname() {
    return fname;
  }

  public void setFname(String fname) {
    this.fname = fname;
  }

  public String getLname() {
    return lname;
  }

  public void setLname(String lname) {
    this.lname = lname;
  }

  public Password getPassword() {
    return password;
  }

  public void setPassword(Password password) {
    this.password = password;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public int getBodyHeight() {
    return bodyHeight;
  }

  public void setBodyHeight(int bodyHeight) {
    this.bodyHeight = bodyHeight;
  }

  public int getBodyWeight() {
    return bodyWeight;
  }

  public void setBodyWeight(int bodyWeight) {
    this.bodyWeight = bodyWeight;
  }

}
