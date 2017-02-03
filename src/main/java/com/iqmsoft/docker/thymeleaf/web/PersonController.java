package com.iqmsoft.docker.thymeleaf.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iqmsoft.docker.thymeleaf.domain.Person;
import com.iqmsoft.docker.thymeleaf.service.PersonRepo;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
@RequestMapping(path = "/person")
public class PersonController {

  @Autowired
public PersonRepo personRepo;

  @RequestMapping(method = POST)
  public Person create(@RequestBody final Person person) {
    return personRepo.save(person);
  }

  @RequestMapping(method = GET, path = "/{id}")
  public Person find(@PathVariable final Long id) {
    return personRepo.findOne(id);
  }

  /**
   * Get the unique list of person names.
   * @return the list of names
   */
  @RequestMapping(method = GET, path = "/names")
  public List<String> getNames() {
    return personRepo.findAll().stream()
            .map(Person::getName).distinct()
            .collect(Collectors.toList());
  }

  @RequestMapping(method = GET)
  public List<Person> getPeople() {
    return personRepo.findAll();
  }
}
