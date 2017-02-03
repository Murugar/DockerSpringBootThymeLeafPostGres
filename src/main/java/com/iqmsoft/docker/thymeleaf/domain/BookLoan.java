package com.iqmsoft.docker.thymeleaf.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


@Entity
public class BookLoan {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @NotNull
  @Column(nullable = false)
  private Long bookId;
  @NotNull
  @Column(nullable = false)
  private Date created = new Date();
  @NotNull
  @Column(nullable = false)
  private Long personId;

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public Long getBookId() {
    return bookId;
  }

  public void setBookId(final Long bookId) {
    this.bookId = bookId;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(final Date created) {
    this.created = created;
  }

  public Long getPersonId() {
    return personId;
  }

  public void setPersonId(final Long personId) {
    this.personId = personId;
  }
}
