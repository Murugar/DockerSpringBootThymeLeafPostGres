package com.iqmsoft.docker.thymeleaf.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.iqmsoft.docker.thymeleaf.init.DataGenerator;
import com.iqmsoft.docker.thymeleaf.service.BookLoanRepo;
import com.iqmsoft.docker.thymeleaf.service.BookRepo;
import com.iqmsoft.docker.thymeleaf.service.PersonRepo;


@Configuration
public class MainAppConfig {

  @Bean
  @Autowired
  public CommandLineRunner getDataGenerator(final BookLoanRepo bookLoanRepo,
                                            final BookRepo bookRepo,
                                            final PersonRepo personRepo) {

    return new DataGenerator(bookLoanRepo, bookRepo, personRepo);
  }
}
