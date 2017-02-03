package com.iqmsoft.docker.thymeleaf.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


@Entity
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @NotNull
  @Column(nullable = false)
  private String author;
  @NotNull
  @Column(nullable = false)
  private Date created = new Date();
  @NotNull
  @Column(nullable = false)
  private String isbn;
  @NotNull
  @Column(nullable = false)
  private String title;

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(final String author) {
    this.author = author;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(final Date created) {
    this.created = created;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(final String isbn) {
    this.isbn = isbn;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(final String title) {
    this.title = title;
  }

  @Override
  public String toString() {
    return getId() + ":" + getTitle();
//    return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
  }
}
