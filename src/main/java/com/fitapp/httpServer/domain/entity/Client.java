package com.fitapp.httpServer.domain.entity;

public class Client {
  Long cid;
  String firstName;
  String lastName;
  int age;
  int bodyHeight;
  int bodyWeight;

  public Long getCid() {
    return cid;
  }

  public void setCid(Long cid) {
    this.cid = cid;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
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
