package com.neoremind.spring.cloud.demo.provider.api;

import com.neoremind.spring.cloud.demo.provider.dto.Person;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * RPC服务接口定义
 *
 * @author xu.zx
 */
@FeignClient("SERVICE-HELLO")
public interface PersonService {

//  void doNothing();
//
//  String echo(String text);
//
//  Person getByIdPrimitive(long id);

  @RequestMapping(value = "/person/getById/{id}", method = RequestMethod.GET)
  Person getByIdBoxing(Long id);

  @RequestMapping(value = "/person/getAll", method = RequestMethod.GET)
  List<Person> getAll();

//  Person add(Person person);
//
//  void deleteById(long id);

}
