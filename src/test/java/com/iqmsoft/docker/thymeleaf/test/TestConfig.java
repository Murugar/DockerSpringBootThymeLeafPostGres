package com.iqmsoft.docker.thymeleaf.test;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.iqmsoft.docker.thymeleaf.service.BookLoanRepo;
import com.iqmsoft.docker.thymeleaf.service.BookRepo;
import com.iqmsoft.docker.thymeleaf.service.PersonRepo;

@Configuration
public class TestConfig {

  @Bean
  public BookRepo getBookRepo() {
    return Mockito.mock(BookRepo.class);
  }

  @Bean
  public BookLoanRepo getBookLoanRepo() {
    return Mockito.mock(BookLoanRepo.class);
  }

  @Bean
  public PersonRepo getPersonRepo() {
    return Mockito.mock(PersonRepo.class);
  }
}
