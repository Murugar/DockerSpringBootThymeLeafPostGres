package com.iqmsoft.docker.thymeleaf.test.web;

import com.iqmsoft.docker.thymeleaf.test.TestApplication;
import com.iqmsoft.docker.thymeleaf.test.TestConfig;
import com.iqmsoft.docker.thymeleaf.web.MainController;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {TestApplication.class, TestConfig.class})
public class MainControllerIntegrationTest {

  @LocalServerPort
  private int port;
  private URL base;

  @Autowired
  private TestRestTemplate template;

  @Before
  public void setUp() throws Exception {
    this.base = new URL("http://localhost:" + port + "/");
  }

  @Test
  public void getHello() throws Exception {
    final String url = base.toString() + "hello";
    final ResponseEntity<String> response = template.getForEntity(url, String.class);
    assertThat(response.getBody(), equalTo(MainController.HELLO));
  }
}
