package com.neoremind.spring.cloud.demo.provider.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Greeting {

  private long id;

  private String content;

}
