package com.neoremind.spring.cloud.demo.provider.api.impl;

import com.google.common.base.Preconditions;

import com.neoremind.spring.cloud.demo.provider.api.PersonService;
import com.neoremind.spring.cloud.demo.provider.dao.PersonDao;
import com.neoremind.spring.cloud.demo.provider.dao.impl.PersonDaoImpl;
import com.neoremind.spring.cloud.demo.provider.dto.Person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * RPC服务实现
 *
 * @author xu.zx
 */
@Service
public class PersonServiceImpl implements PersonService {

  private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceImpl.class);

  private PersonDao personDao;

  public PersonServiceImpl() {
    this.personDao = new PersonDaoImpl();
  }
//
//  @Override
//  public void doNothing() {
//    LOGGER.info("do nothing");
//  }
//
//  @Override
//  public String echo(String text) {
//    return text;
//  }
//
//  @Override
//  public Person getByIdPrimitive(long id) {
//    return personDao.getById(id);
//  }

  @Override
  public Person getByIdBoxing(Long id) {
    return personDao.getById(id);
  }

  @Override
  public List<Person> getAll() {
    return personDao.getAll();
  }

//  @Override
//  public Person add(Person person) {
//    Preconditions.checkNotNull(person);
//    Preconditions.checkArgument(person.getId() != null, "Id should not be null");
//    return personDao.add(person);
//  }
//
//  @Override
//  public void deleteById(long id) {
//    personDao.deleteById(id);
//  }

}
