package com.fitapp.httpServer.domain.entity;

public class Measurement {
  Long measurementId;
  String firstName;
  String LastName;
  int age;
  int bodyHeight;
  int bodyWeight;

  public Long getMeasurementId() {
    return measurementId;
  }

  public void setMeasurementId(Long measurementId) {
    this.measurementId = measurementId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return LastName;
  }

  public void setLastName(String lastName) {
    LastName = lastName;
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
