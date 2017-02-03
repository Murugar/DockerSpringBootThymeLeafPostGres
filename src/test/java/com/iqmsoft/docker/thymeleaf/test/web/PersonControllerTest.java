package com.iqmsoft.docker.thymeleaf.test.web;

import com.iqmsoft.docker.thymeleaf.domain.Person;
import com.iqmsoft.docker.thymeleaf.init.DataProvider;
import com.iqmsoft.docker.thymeleaf.service.PersonRepo;
import com.iqmsoft.docker.thymeleaf.test.TestApplication;
import com.iqmsoft.docker.thymeleaf.test.TestConfig;
import com.iqmsoft.docker.thymeleaf.test.util.TestUtil;
import com.iqmsoft.docker.thymeleaf.web.PersonController;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestApplication.class, TestConfig.class})
@AutoConfigureMockMvc
public class PersonControllerTest {

  private static final String BASE_URL = "/person";

  @Autowired
  private MockMvc mvc;
  @Autowired
  private PersonController controller;

  private PersonRepo personRepo;

  @Before
  public void setUp() throws Exception {
    personRepo = mock(PersonRepo.class);
    controller.personRepo = personRepo;
  }

  @Test
  public void create() throws Exception {
    final Person person = DataProvider.getPerson();
    when(personRepo.save(any(Person.class))).thenReturn(person);

    mvc.perform(MockMvcRequestBuilders.post(BASE_URL)
            .content(TestUtil.convertObjectToJsonBytes(person))
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", is(person.getName())));

    verify(personRepo, times(1)).save(any(Person.class));
  }

  @Test
  public void find() throws Exception {
    final long id = 1;
    final Person person = DataProvider.getPerson();
    when(personRepo.findOne(id)).thenReturn(person);

    mvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/" + id)
            .accept(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", is(person.getName())));

    verify(personRepo, times(1)).findOne(id);
  }

  @Test
  public void getNames() throws Exception {
    final List<Person> personList = new ArrayList<>();
    personList.add(DataProvider.getPerson());
    when(personRepo.findAll()).thenReturn(personList);

    mvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/names")
            .accept(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0]", is(personList.get(0).getName())));

    verify(personRepo, times(1)).findAll();
  }

  @Test
  public void getPeople() throws Exception {
    final List<Person> personList = new ArrayList<>();
    personList.add(DataProvider.getPerson());
    when(personRepo.findAll()).thenReturn(personList);

    mvc.perform(MockMvcRequestBuilders.get(BASE_URL)
            .accept(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].name", is(personList.get(0).getName())));

    verify(personRepo, times(1)).findAll();
  }
}
