package com.iqmsoft.docker.thymeleaf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SuppressWarnings({"checkstyle:hideutilityclassconstructor","PMD"})
public class Application {

  private static final Logger LOG = LoggerFactory.getLogger(Application.class);

  /**
   * Main application entry point.
   * @param args command line arguments
   */
  public static void main(String[] args) {
    LOG.info(">>>> Starting spring boot application!! <<<<");
    SpringApplication.run(Application.class, args);
  }
}
