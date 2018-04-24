package com.neoremind.spring.cloud.demo.provider.dao;


import com.neoremind.spring.cloud.demo.provider.dto.Person;

import java.util.List;

/**
 * PersonDao
 *
 * @author xu.zx
 */
public interface PersonDao {

  Person add(Person person);

  void deleteById(long id);

  Person getById(long id);

  List<Person> getAll();

}
