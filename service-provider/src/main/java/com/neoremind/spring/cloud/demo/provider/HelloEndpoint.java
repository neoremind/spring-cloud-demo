package com.neoremind.spring.cloud.demo.provider;

import com.neoremind.spring.cloud.demo.provider.dto.Greeting;
import com.netflix.appinfo.EurekaInstanceConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HelloEndpoint {

  @Autowired
  private EurekaInstanceConfig eurekaInstanceConfig;

  @Value("${server.port}")
  private int serverPort = 0;

  @RequestMapping(value = "/hello", method = RequestMethod.GET)
  public String hello() {
    log.info("/hello, instanceId:{}, host:{}", eurekaInstanceConfig.getInstanceId(), eurekaInstanceConfig.getHostName(false));
    return "Hello, Spring Cloud! My port is " + String.valueOf(serverPort);
  }

  private static final String template = "Hello, %s!";

  private final AtomicLong counter = new AtomicLong();

  @RequestMapping(value = "/greeting", method = RequestMethod.GET)
  public Greeting greeting(@RequestParam(value = "text", defaultValue = "hi") String text) {
    return new Greeting(counter.incrementAndGet(),
        String.format(template, text));
  }
}
