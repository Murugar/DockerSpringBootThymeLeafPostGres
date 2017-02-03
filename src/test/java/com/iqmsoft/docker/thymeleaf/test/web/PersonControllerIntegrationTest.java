package com.iqmsoft.docker.thymeleaf.test.web;

import com.iqmsoft.docker.thymeleaf.domain.Person;
import com.iqmsoft.docker.thymeleaf.init.DataProvider;
import com.iqmsoft.docker.thymeleaf.service.PersonRepo;
import com.iqmsoft.docker.thymeleaf.test.TestApplication;
import com.iqmsoft.docker.thymeleaf.test.TestConfig;
import com.iqmsoft.docker.thymeleaf.web.PersonController;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasJsonPath;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.isJson;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {TestApplication.class, TestConfig.class})
@SuppressWarnings("PMD.JUnitTestContainsTooManyAsserts")
public class PersonControllerIntegrationTest {

  @LocalServerPort
  private int port;
  private String baseUrl;

  @Autowired
  private TestRestTemplate template;
  @Autowired
  private PersonController controller;
  private PersonRepo personRepo;

  @Before
  public void setUp() throws Exception {
    this.baseUrl = "http://localhost:" + port + "/person";
    personRepo = mock(PersonRepo.class);
    controller.personRepo = personRepo;
  }

  @Test
  public void create() throws Exception {
    final Person person = DataProvider.getPerson();
    when(personRepo.save(any(Person.class))).thenReturn(person);

    final ResponseEntity<Person> response = template.postForEntity(baseUrl, person, Person.class);
    assertThat(response.getBody().getName(), equalTo(person.getName()));
    verify(personRepo, times(1)).save(any(Person.class));
  }

  @Test
  public void find() throws Exception {
    final Person person = DataProvider.getPerson();
    when(personRepo.findOne(anyLong())).thenReturn(person);

    final long id = 1;
    final String url = baseUrl + "/" + id;
    final ResponseEntity<Person> response = template.getForEntity(url, Person.class);
    assertThat(response.getBody().getName(), equalTo(person.getName()));
    verify(personRepo, times(1)).findOne(id);
  }

  @Test
  public void getNames() throws Exception {
    final List<Person> personList = getPersonList();
    when(personRepo.findAll()).thenReturn(personList);

    final String url = baseUrl + "/names";
    final ResponseEntity<String> response = template.getForEntity(url, String.class);
    assertThat(response.getBody(), isJson());
    assertThat(response.getBody(), hasJsonPath("$", hasSize(personList.size())));
    verify(personRepo, times(1)).findAll();
  }

  private List<Person> getPersonList() {
    final List<Person> personList = new ArrayList<>();
    personList.add(DataProvider.getPerson());
    personList.add(DataProvider.getPerson());
    return personList;
  }

  @Test
  public void getPeople() throws Exception {
    final List<Person> personList = getPersonList();
    when(personRepo.findAll()).thenReturn(personList);

    final List<Person> response = template.getForObject(baseUrl, List.class);
    assertThat(response, isJson());
    assertThat(response, hasJsonPath("$", hasSize(personList.size())));
    assertThat(response, hasJsonPath("$[0].name", equalTo(personList.get(0).getName())));
    verify(personRepo, times(1)).findAll();
  }
}
