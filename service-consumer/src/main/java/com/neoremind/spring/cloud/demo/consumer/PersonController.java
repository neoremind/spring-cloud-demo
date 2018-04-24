package com.neoremind.spring.cloud.demo.consumer;

import com.neoremind.spring.cloud.demo.provider.api.PersonService;
import com.neoremind.spring.cloud.demo.provider.dto.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class PersonController {

  @Autowired
  private PersonService personService;

  @RequestMapping(value = "/person/getAll", method = RequestMethod.GET)
  public List<Person> getAll() {
    return personService.getAll();
  }

  @RequestMapping(value = "/person/getById/{id}", method = RequestMethod.GET)
  public Person getById(@PathVariable Long id) {
    return personService.getByIdBoxing(id);
  }
}
