package com.iqmsoft.docker.thymeleaf.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class })
@ComponentScan(basePackages = {"com.iqmsoft.docker.thymeleaf.web"})
@SuppressWarnings({"checkstyle:hideutilityclassconstructor","PMD"})
public class TestApplication {

  private static final Logger LOG = LoggerFactory.getLogger(TestApplication.class);

  /**
   * Main application entry point.
   * @param args command line arguments
   */
  public static void main(String[] args) {
    LOG.info(">>>> Starting spring test boot application!! <<<<");
    SpringApplication.run(TestApplication.class, args);
  }
}
