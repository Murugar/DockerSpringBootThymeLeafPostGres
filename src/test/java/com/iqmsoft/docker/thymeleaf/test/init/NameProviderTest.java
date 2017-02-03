package com.iqmsoft.docker.thymeleaf.test.init;

import org.junit.Test;

import com.iqmsoft.docker.thymeleaf.init.NameProvider;

import static org.junit.Assert.assertFalse;


public class NameProviderTest {

  @Test
  public void getLastName() throws Exception {
    final String name = NameProvider.getLastName();
    assertFalse(name.isEmpty());
  }

  @Test
  public void getFirstName() throws Exception {
    final String name = NameProvider.getFirstName();
    assertFalse(name.isEmpty());
  }

  @Test
  public void getBookTitle() throws Exception {
    final String title = NameProvider.getBookTitle();
    assertFalse(title.isEmpty());
  }

  @Test
  public void getName() throws Exception {
    final String name = NameProvider.getName();
    assertFalse(name.isEmpty());
  }

  @Test
  public void getStreetName() throws Exception {
    final String name = NameProvider.getStreetName();
    assertFalse(name.isEmpty());
  }

  @Test
  public void getStreetSuffix() throws Exception {
    final String name = NameProvider.getStreetSuffix();
    assertFalse(name.isEmpty());
  }
}