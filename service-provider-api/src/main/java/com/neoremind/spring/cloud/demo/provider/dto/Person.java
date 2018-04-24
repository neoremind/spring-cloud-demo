package com.neoremind.spring.cloud.demo.provider.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Person
 *
 * @author xu.zx
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {

  private Long id;

  private String name;

  private int age;

  private boolean adult;

  private Gender gender;

  private List<String> alias;

  private List<Address> addressList;

}
