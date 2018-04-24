package com.neoremind.spring.cloud.demo.provider.dao.impl;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.primitives.Longs;

import com.neoremind.spring.cloud.demo.provider.dao.PersonDao;
import com.neoremind.spring.cloud.demo.provider.dto.Address;
import com.neoremind.spring.cloud.demo.provider.dto.Gender;
import com.neoremind.spring.cloud.demo.provider.dto.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * 内存中维护多个Person，对外提供CRUD API。
 *
 * @author xu.zx
 */
public class PersonDaoImpl implements PersonDao {

  private Map<Long, Person> personMap;

  public PersonDaoImpl() {
    personMap = Maps.newConcurrentMap();

    personMap.put(100L, Person.builder()
        .id(100L)
        .name("jack")
        .adult(true)
        .age(26)
        .gender(Gender.MALE)
        .alias(Lists.newArrayList("jackie"))
        .addressList(Lists.newArrayList(
            createAddress(555, "Beijing")
        )).build());

    personMap.put(200L, Person.builder()
        .id(200L)
        .name("james")
        .adult(false)
        .age(50)
        .gender(Gender.MALE)
        .addressList(Lists.newArrayList(
            createAddress(666, "LA"),
            createAddress(777, "NYC")
        )).build());

    personMap.put(300L, Person.builder()
        .id(300L)
        .name("lucy")
        .build());
  }

  @Override
  public Person add(Person person) {
    personMap.put(person.getId(), person);
    return person;
  }

  @Override
  public void deleteById(long id) {
    personMap.remove(id);
  }

  @Override
  public Person getById(long id) {
    Preconditions.checkNotNull(personMap);
    return personMap.get(id);
  }

  @Override
  public List<Person> getAll() {
    Preconditions.checkNotNull(personMap);
    List<Person> personList = new ArrayList<>(personMap.values());
    Collections.sort(personList, new Comparator<Person>() {
      @Override
      public int compare(Person o1, Person o2) {
        return Longs.compare(o1.getId(), o2.getId());
      }
    });
    return personList;
  }

  private Address createAddress(int zipcode, String address) {
    return Address.builder().zipcode(zipcode).address(address).build();
  }

}
