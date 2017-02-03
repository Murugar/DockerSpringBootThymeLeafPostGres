package com.iqmsoft.docker.thymeleaf.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @NotNull
  @Column(nullable = false)
  private Date created = new Date();
  @NotNull
  @Column(nullable = false)
  private String email;
  @NotNull
  @Column(nullable = false)
  private String name;
  private String phone;

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(final Date created) {
    this.created = created;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(final String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(final String phone) {
    this.phone = phone;
  }

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
  }
}
