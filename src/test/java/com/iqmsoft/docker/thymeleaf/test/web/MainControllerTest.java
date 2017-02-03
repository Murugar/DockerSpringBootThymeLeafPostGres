package com.iqmsoft.docker.thymeleaf.test.web;

import com.iqmsoft.docker.thymeleaf.test.TestApplication;
import com.iqmsoft.docker.thymeleaf.test.TestConfig;
import com.iqmsoft.docker.thymeleaf.web.MainController;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestApplication.class, TestConfig.class})
@AutoConfigureMockMvc
public class MainControllerTest {

  @Autowired
  private MockMvc mvc;

  @Test
  public void getHello() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get("/hello")
            .accept(MediaType.TEXT_PLAIN_VALUE))
            .andExpect(status().isOk())
            .andExpect(content().string(equalTo(MainController.HELLO)));
  }
}
