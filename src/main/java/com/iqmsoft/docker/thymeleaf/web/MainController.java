package com.iqmsoft.docker.thymeleaf.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

@RestController
public class MainController {

  public static final String HELLO = "Hello World";

  @RequestMapping("/")
  public void index(final HttpServletResponse response) throws IOException {
    response.sendRedirect("/free");
  }

  @RequestMapping("/hello")
  public String index() {
    return HELLO;
  }
}
