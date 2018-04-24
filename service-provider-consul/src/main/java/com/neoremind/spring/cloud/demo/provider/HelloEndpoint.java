package com.neoremind.spring.cloud.demo.provider;

import com.neoremind.spring.cloud.demo.provider.dto.Greeting;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HelloEndpoint {

  @RequestMapping(value = "/hello", method = RequestMethod.GET)
  public String hello() {
    return "Hello, Spring Cloud!";
  }

  private static final String template = "Hello, %s!";

  private final AtomicLong counter = new AtomicLong();

  @RequestMapping(value = "/greeting", method = RequestMethod.GET)
  public Greeting greeting(@RequestParam(value = "text", defaultValue = "hi") String text) {
    return new Greeting(counter.incrementAndGet(),
        String.format(template, text));
  }
}
