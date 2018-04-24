package com.neoremind.spring.cloud.demo.provider.dto;

/**
 * Gender枚举
 *
 * @author xu.zx
 */
public enum Gender {

  MALE("male"),
  FEMALE("female");

  private String value;

  Gender(String value) {
    this.value = value;
  }

  public String value() {
    return value;
  }
}
